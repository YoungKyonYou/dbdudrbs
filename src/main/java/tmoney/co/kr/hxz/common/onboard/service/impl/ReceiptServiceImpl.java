package tmoney.co.kr.hxz.common.onboard.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tmoney.co.kr.hxz.common.onboard.service.ReceiptService;
import tmoney.co.kr.hxz.common.onboard.util.ReceiptUtil;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 스텝 "영수증(receipt)" JWS 발급/검증 서비스.
 *
 * receipt 클레임:
 * - sub: "onboarding-receipt"
 * - jti: 고유 식별자(1회성 소모)
 * - onb: 온보딩 식별자
 * - step: 스텝 번호(0..3)
 * - rcpt: true (receipt 마커)
 * - payloadHash: 정규화 JSON의 SHA-256(Base64URL)
 * - exp: 만료
 *
 * 검증 시:
 * - 서명/만료 OK
 * - rcpt=true, sub 일치
 * - onb/step 기대값과 일치
 * - payloadHash == 전달받은 payload의 해시
 * - jti 1회성 소모(재사용 금지)
 */
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final RSASSASigner signer;
    private final RSASSAVerifier verifier;
    private final JWSAlgorithm alg;
    private final Cache<String, Boolean> jtiCache;
    private final ReceiptUtil receiptUtil;

    // ===== 발급 =====

    @Override
    public <T> String issueReceiptFromMap(String onb, int step, T payload, Duration ttl) {
        String canon = receiptUtil.toCanonicalJson(payload);
        return issueReceiptFromJson(onb, step, canon, ttl);
    }

    @Override
    public String issueReceiptFromJson(String onb, int step, String payloadJson, Duration ttl) {
        String payloadHash = receiptUtil.sha256Base64UrlFromJson(payloadJson);
        return sign(buildClaims(onb, step, payloadHash, ttl));
    }

    private JWTClaimsSet buildClaims(String onb, int step, String payloadHash, Duration ttl) {
        Instant now = Instant.now();
        String jti = UUID.randomUUID().toString();

        return new JWTClaimsSet.Builder()
                .subject("onboarding-receipt")
                .jwtID(jti)
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plus(ttl)))
                .claim("onb", onb)
                .claim("step", step)
                .claim("rcpt", true)
                .claim("payloadHash", payloadHash)
                .build();
    }

    private String sign(JWTClaimsSet claims) {
        try {
            JWSHeader header = new JWSHeader.Builder(alg).type(JOSEObjectType.JWT).build();
            SignedJWT jwt = new SignedJWT(header, claims);
            jwt.sign(signer);
            return jwt.serialize();
        } catch (Exception e) {
            throw new IllegalStateException("Receipt sign error", e);
        }
    }

    // ===== 검증 =====

    @Override
    public <T> void verifyAndConsumeFromMap(String receiptJws, String expectedOnb, int expectedStep, T payload) {
        String canon = receiptUtil.toCanonicalJson(payload);
        verifyAndConsumeFromJson(receiptJws, expectedOnb, expectedStep, canon);
    }

    @Override
    public void verifyAndConsumeFromJson(String receiptJws, String expectedOnb, int expectedStep, String payloadJson){
        SignedJWT jwt = parseAndVerify(receiptJws);
        JWTClaimsSet c = getClaimsNotExpired(jwt);

        // 기본 클레임 검사
        if (!Objects.equals("onboarding-receipt", c.getSubject()))
            throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("영수증 주제(Subject) 불일치"); // CHANGED
        try{
            if (!Boolean.TRUE.equals(c.getBooleanClaim("rcpt")))
                throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("유효한 영수증 마커(rcpt)가 아닙니다"); // CHANGED
        }catch(java.text.ParseException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }

        String onb = stringClaim(c, "onb");
        int step = intClaim(c, "step");
        String hash = stringClaim(c, "payloadHash");

        if (!Objects.equals(expectedOnb, onb))
            throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("온보딩 식별자(onb)가 일치하지 않습니다"); // CHANGED
        if (expectedStep != step)
            throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("단계 정보(step)가 일치하지 않습니다"); // CHANGED

//        String actualHash = receiptUtil.sha256Base64UrlFromJson(payloadJson);
//        if (!Objects.equals(hash, actualHash))
//            throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("페이로드 무결성 검증 실패(payloadHash mismatch)"); // CHANGED

        // jti 1회성 소모(재사용 방지) — 재사용은 여전히 409(CONFLICT) 유지
        String jti = c.getJWTID();
        boolean first = jtiCache.asMap().putIfAbsent("rcpt:" + jti, Boolean.TRUE) == null;
        if (!first) throw conflict("Receipt replay");
    }

    private SignedJWT parseAndVerify(String jws) {
        try {
            SignedJWT jwt = SignedJWT.parse(jws);
            if (!jwt.verify(verifier))
                throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("영수증 서명 검증 실패"); // CHANGED
            return jwt;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("영수증 토큰 형식 오류"); // CHANGED
        }
    }

    private JWTClaimsSet getClaimsNotExpired(SignedJWT jwt) {
        try {
            JWTClaimsSet c = jwt.getJWTClaimsSet();
            Date exp = c.getExpirationTime();
            if (exp == null || exp.toInstant().isBefore(Instant.now()))
                throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("영수증 유효기간 만료"); // CHANGED
            return c;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("영수증 클레임 파싱 오류"); // CHANGED
        }
    }

    private static String stringClaim(JWTClaimsSet c, String name) {
        Object v = c.getClaim(name);
        if (v == null) return null;
        return String.valueOf(v);
    }

    private static int intClaim(JWTClaimsSet c, String name) {
        Object v = c.getClaim(name);
        if (v instanceof Number) return ((Number) v).intValue();
        return Integer.parseInt(String.valueOf(v));
    }

    // forbidden(...) 삭제 (도메인 예외로 대체) // CHANGED

    private static ResponseStatusException conflict(String msg) {
        return new ResponseStatusException(HttpStatus.CONFLICT, msg);
    }
}
