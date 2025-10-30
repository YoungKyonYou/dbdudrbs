package tmoney.co.kr.hxz.common.onboard.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;
import tmoney.co.kr.hxz.common.onboard.service.TokenService;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    private final RSASSASigner signer;
    private final RSASSAVerifier verifier;
    private final JWSAlgorithm alg;
    private static final SecureRandom RNG = new SecureRandom();

    public TokenServiceImpl(RSASSASigner signer, RSASSAVerifier verifier, JWSAlgorithm alg) {
        this.signer = signer;
        this.verifier = verifier;
        this.alg = alg;
    }

    /** 진행용(중간 단계) 토큰 발급 */
    @Override
    public String issue(String onb, int step, int mask, Duration ttl) {
        Instant now = Instant.now();
        String jti = rand(16);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject("onboarding")                // 목적
                .jwtID(jti)                            // 재사용 방지 키
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plus(ttl)))
                .claim("onb", onb)
                .claim("step", step)
                .claim("mask", mask)
                // 완료 토큰과 구분하기 위해 명시적으로 done=false(선택)
                .claim("done", false)
                .build();

        return sign(claims);
    }

    /**
     * 최종 완료 토큰 발급
     * - 완료 페이지만 접근 가능하도록 done=true 포함
     * - finalStep(보통 4), mask(모든 단계 완료 비트) 포함
     * - 짧은 TTL 권장(예: 60초)
     */
    @Override
    public String issueFinal(String onb, int finalStep, int mask, boolean done, Duration ttl) {
        Instant now = Instant.now();
        String jti = rand(16);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject("onboarding")                // 동일 subject 유지
                .jwtID(jti)
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plus(ttl)))
                .claim("onb", onb)
                .claim("step", finalStep)
                .claim("mask", mask)
                .claim("done", done)                  // 완료 표식
                .build();

        return sign(claims);
    }

    /** 토큰 서명 공통 루틴 */
    private String sign(JWTClaimsSet claims) {
        try {
            JWSHeader header = new JWSHeader.Builder(alg)
                    .type(JOSEObjectType.JWT)
                    .build();
            SignedJWT jwt = new SignedJWT(header, claims);
            jwt.sign(signer);
            return jwt.serialize();
        } catch (JOSEException e) {
            throw new IllegalStateException("JWT sign error", e);
        }
    }

    /** 검증(서명/만료) */
    @Override
    public JWTClaimsSet verify(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            if (!jwt.verify(verifier)) throw new IllegalArgumentException("Invalid signature");
            Date exp = jwt.getJWTClaimsSet().getExpirationTime();
            if (exp == null || exp.toInstant().isBefore(Instant.now()))
                throw new IllegalArgumentException("Expired");
            return jwt.getJWTClaimsSet();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token", e);
        }
    }

    @Override
    public String rand(int bytes) {
        byte[] b = new byte[bytes];
        RNG.nextBytes(b);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(b);
    }
}
