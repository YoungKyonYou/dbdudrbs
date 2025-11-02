package tmoney.co.kr.hxz.etc.mbrsjoin.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import tmoney.co.kr.hxz.common.onboard.domain.PrecheckContext;
import tmoney.co.kr.hxz.common.onboard.service.OnboardingFlowService;
import tmoney.co.kr.hxz.common.onboard.service.ReceiptService;
import tmoney.co.kr.hxz.common.onboard.util.NonceUtil;
import tmoney.co.kr.hxz.common.onboard.util.OnboardingWebUtil;
import tmoney.co.kr.hxz.common.onboard.vo.SignupVO;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;
import tmoney.co.kr.hxz.etc.mbrsjoin.mapper.MbrsJoinMapper;
import tmoney.co.kr.hxz.etc.mbrsjoin.service.MbrsJoinService;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.PrsnAuthReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.rcpt.MbrsJoinFinalizeVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;

@Service
@Validated
public class MbrsJoinServiceImpl implements MbrsJoinService {
    private final NonceUtil nonceUtil;
    private final ReceiptService receiptService;
    private final Cache<String, Boolean> jtiCache;
    private final OnboardingFlowService flow;
    private final Integer onbMaxAgeSeconds;
    private final Integer receiptStepTtlMinutes;
    private final MbrsJoinMapper mbrsJoinMapper;

    public MbrsJoinServiceImpl(NonceUtil nonceUtil,
                               ReceiptService receiptService,
                               @Qualifier("jtiCache") Cache<String, Boolean> jtiCache,
                               OnboardingFlowService flow,
                               @Value("${app.onboarding.cookie.onb-max-age-seconds}") Integer onbMaxAgeSeconds,
                               @Value("${app.onboarding.receipt.step-ttl-minutes}") Integer receiptStepTtlMinutes,
                               MbrsJoinMapper mbrsJoinMapper
    ) {
        this.nonceUtil = nonceUtil;
        this.receiptService = receiptService;
        this.flow = flow;
        this.jtiCache = jtiCache;
        this.onbMaxAgeSeconds = onbMaxAgeSeconds;
        this.receiptStepTtlMinutes = receiptStepTtlMinutes;
        this.mbrsJoinMapper = mbrsJoinMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public void start(HttpServletRequest req, HttpServletResponse res) {
        String onb = OnboardingWebUtil.rand(24);
        String token = flow.issueStartToken(onb);      // step=0, mask=0
        // 진행 쿠키 저장
        OnboardingWebUtil.writeCookie(req, res, "onb", token, onbMaxAgeSeconds);  // 진행 토큰

        // 이전 완료 쿠키 제거
        OnboardingWebUtil.expireCookie(req, res, "onb_done");
    }

    @Override
    @Transactional(readOnly = true)
    public SignupVO mbrsJoinStep2(HttpServletRequest req, Model model) {
        JWTClaimsSet c = (JWTClaimsSet) req.getAttribute("claims");

        int step = ((Number) c.getClaim("step")).intValue();

        String onb = (String) c.getClaim("onb");
        String jti  = c.getJWTID();
        String nonce = nonceUtil.issue(onb, step, jti);
        return new SignupVO(step, nonce);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> submitAuthResult(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        // nonce/token 검증
        PrecheckContext ctx = flow.precheck(token, nonce, 0);

        // 본인 인증 결과 payload 예시
//         {
//           "prsnAuthCiEncVal": "ENCRYPTED_CI_VALUE",
//           "gndrCd": "M",
//           "mbrsBrdt": "19940101"
//         }

        // receipt 발급 (Step3로 넘길 데이터)
        String receipt = receiptService.issueReceiptFromMap(
                ctx.getOnb(),
                0, // step index
                new PrsnAuthReqVO("010-3843-2389","ENCRYPTED_CI_VALUE", "M", "19940101"), // 인증 데이터
                Duration.ofMinutes(receiptStepTtlMinutes)
        );

        // 다음 step용 nonce 발급
        String nextToken = flow.advance(req, res, ctx, 1);
        String nextNonce = flow.issueNonceFor(nextToken);

        return Map.of(
                "receipt", receipt,
                "nextNonce", nextNonce
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> submitInfResult(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            MbrsJoinInstReqVO payload,
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        // nonce/token 검증
        PrecheckContext ctx = flow.precheck(token, nonce, 1);

        flow.finishAndIssueDoneToken(req, res, ctx); // onb_done 쿠키 발급

        // receipt 발급
        String receipt = receiptService.issueReceiptFromMap(
                ctx.getOnb(),
                1, // step index
                payload, // 회원정보 데이터
                Duration.ofMinutes(receiptStepTtlMinutes)
        );
        return Map.of(
                "receipt", receipt
        );
    }

    @Override
    @Transactional
    public void insertMbrsJoin(String finalToken, MbrsJoinFinalizeVO body) {

        JWTClaimsSet c = flow.verifyDoneToken(finalToken);
        String onb = (String) c.getClaim("onb");

        receiptService.verifyAndConsumeFromMap(OnboardingWebUtil.reqOr("rcpt1", body.getRcpt1()), onb, 0, body.getStep1());
        receiptService.verifyAndConsumeFromMap(OnboardingWebUtil.reqOr("rcpt2", body.getRcpt2()), onb, 1, body.getStep2());

        PrsnAuthReqVO authReq = body.getStep1();
        MbrsJoinInstReqVO req = body.getStep2();

        // 아이디 중복 체크
        if (readMbrsCountById(req.getMbrsId())) {
            throw DomainExceptionCode.SIGNUP_ID_DUPLICATION_ERROR.newInstance("이미 사용중인 아이디입니다.");
        }

        // 비밀번호 확인 일치 검증
        if (!req.getPwd().equals(req.getCfmPwd())) {
            throw DomainExceptionCode.PASSWORD_VALIDATION_ERROR.newInstance("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 아이디 포함 검증
        if (req.getPwd().toLowerCase().contains(req.getMbrsId().toLowerCase())) {
            throw DomainExceptionCode.SIGNUP_PASSWORD_CONTAINS_ID.newInstance();
        }

        // 암호화
//        String encodeMbrsNm = passwordEncoder.encode(req.getMbrsNm());
//        String encodePwd = passwordEncoder.encode(req.getPwd());


        MbrsJoinReqVO reqVO = new MbrsJoinReqVO(
                req.getMbrsId(),
                req.getMbrsNm(),
                req.getMailAddr(),
                authReq.getMbrsMbphNo(),
                req.getMbrsTelNo(),
                req.getPwd(),
                "00",
                0,
                null,
                null,
                "01",
                null,
                null,
                authReq.getPrsnAuthCiEncVal(),
                authReq.getGndrCd(),
                authReq.getMbrsBrdt(),
                null,
                "N",
                req.getMrkgUtlzAgrmYn(),
                req.getSmsRcvAgrmYn(),
                req.getMailRcvAgrmYn(),
                "Y"
        );
        mbrsJoinMapper.insertMbrsJoin(reqVO);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean readMbrsCountById(String checkId) {
        return mbrsJoinMapper.readMbrsCountById(checkId) > 0;
    }
}
