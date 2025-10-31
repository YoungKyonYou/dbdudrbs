package tmoney.co.kr.hxz.etc.mbrsjoin.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.common.onboard.domain.PrecheckContext;
import tmoney.co.kr.hxz.common.onboard.service.OnboardingFlowService;
import tmoney.co.kr.hxz.common.onboard.service.ReceiptService;
import tmoney.co.kr.hxz.common.onboard.util.NonceUtil;
import tmoney.co.kr.hxz.common.onboard.util.OnboardingWebUtil;
import tmoney.co.kr.hxz.common.onboard.vo.SignupVO;
import tmoney.co.kr.hxz.common.onboard.vo.Step2VO;
import tmoney.co.kr.hxz.common.onboard.vo.Step3VO;
import tmoney.co.kr.hxz.etc.mbrsjoin.service.MbrsJoinService;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.CheckIdReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.PrsnAuthReqVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;
import java.util.Map;

@Controller
@RequestMapping("/etc/mbrsjoin")
@Validated
public class MbrsJoinController {
    private final NonceUtil nonceUtil;
    private final ReceiptService receiptService;
    private final Cache<String, Boolean> jtiCache;
    private final OnboardingFlowService flow;
    private final Integer onbMaxAgeSeconds;
    private final Integer receiptStepTtlMinutes;
    private final MbrsJoinService mbrsJoinService;

    public MbrsJoinController(NonceUtil nonceUtil,
                            ReceiptService receiptService,
                            @Qualifier("jtiCache") Cache<String, Boolean> jtiCache,
                            OnboardingFlowService flow,
                            @Value("${app.onboarding.cookie.onb-max-age-seconds}") Integer onbMaxAgeSeconds,
                            @Value("${app.onboarding.receipt.step-ttl-minutes}") Integer receiptStepTtlMinutes,
                              MbrsJoinService mbrsJoinService
                              ) {
        this.nonceUtil = nonceUtil;
        this.receiptService = receiptService;
        this.jtiCache = jtiCache;
        this.flow = flow;
        this.onbMaxAgeSeconds = onbMaxAgeSeconds;
        this.receiptStepTtlMinutes = receiptStepTtlMinutes;
        this.mbrsJoinService = mbrsJoinService;
    }



    /**
     * 회원 가입 화면 조회 step1
     *
     * [process]
     * 1. 약관 동의 화면
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step1.do")
    public String readMbrsJoinStep1() {
        return "/hxz/etc/mbrsjoin/step1";
    }

    /**
     * 약관 동의 결과 저장 후 다음단계 이동
     */
    @PostMapping("/step1")
    public String submitStep1(
            @RequestParam("agrm1") boolean agrm1,
            @RequestParam("agrm2") boolean agrm2,
            @RequestParam("agrm3") boolean agrm3,
            Model model
    ) {
        // 필수 약관 미동의 시 에러 처리
        if (!agrm1 || !agrm2 || !agrm3) {
            model.addAttribute("error", "필수 약관에 모두 동의해야 합니다.");
            return "mbrsjoin/step1";
        }

        return "redirect:/etc/mbrsjoin/step2.do";
    }

    /**
     * 회원 가입 화면 조회 step2 start
     *
     * [process]
     * 1. [step2] 본인 인증 화면
     *
     * @return
     */
    @GetMapping("/step2.do")
    public String start(HttpServletRequest req, HttpServletResponse res) {
        String onb = OnboardingWebUtil.rand(24);
        String token = flow.issueStartToken(onb);      // step=0, mask=0

        // 진행 쿠키 저장
        OnboardingWebUtil.writeCookie(req, res, "onb", token, onbMaxAgeSeconds);  // 진행 토큰

        // 이전 완료 쿠키 제거
        OnboardingWebUtil.expireCookie(req, res, "onb_done");            // 이전 완료 토큰 제거
        return "redirect:/etc/mbrsjoin/step2";
    }

    /**
     * 회원 가입 화면 조회 step2
     *
     * [process]
     * 1. [step2] 본인 인증 화면
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step2")
    public String mbrsJoinStep2(HttpServletRequest req, Model model) {
        JWTClaimsSet c = (JWTClaimsSet) req.getAttribute("claims");
        int step = ((Number) c.getClaim("step")).intValue();

        String onb = (String) c.getClaim("onb");
        String jti  = c.getJWTID();
        String nonce = nonceUtil.issue(onb, step, jti);
        model.addAttribute("signup", new SignupVO(step, nonce));
        return "/hxz/etc/mbrsjoin/step2";
    }


    /**
     * [STEP 2 완료 → STEP 3 진입용] 본인인증 결과 수신
     */
    @PostMapping(value = "/step/complete", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Map<String, String> submitAuthResult(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            HttpServletRequest req, HttpServletResponse res) {

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
                new PrsnAuthReqVO("ENCRYPTED_CI_VALUE", "M", "19940101"), // 인증 데이터
                Duration.ofMinutes(receiptStepTtlMinutes)
        );

        // 다음 step용 nonce 발급
        String nextToken = flow.advance(req, res, ctx, 1);
        String nextNonce = flow.issueNonceFor(nextToken);

        // 프론트로 응답 (step3 이동 시 사용)
        return Map.of(
                "receipt", receipt,
                "nextNonce", nextNonce
        );
    }

    /**
     * 회원 가입 화면 조회 step3
     *
     * [process]
     * 1. 회원 가입 화면 step3 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step3.do")
    public String readMbrsJoinStep3() {
        return "/hxz/etc/mbrsjoin/step3";
    }

    /**
     * 회원 정보 추가
     *
     * tbhxzm101 HXZ_회원정보
     * tbhxzh101 HXZ_회원정보변경이력
     *
     * [process]
     * 1. HXZ_회원정보 테이블 내의 회원 정보 추가
     * 2. HXZ_회원정보변경이력 테이블 회원정보 변경 이력 추가
     *
     * @param req
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> insertMbrsJoin(
            @CookieValue("onb_done") String finalToken,
            @Valid @RequestBody MbrsJoinInstReqVO req
    ) {
        JWTClaimsSet c = flow.verifyDoneToken(finalToken);
        mbrsJoinService.insertMbrsJoin(req);
        return ResponseEntity.ok().build();
    }

    /**
     * 아이디 중복 확인
     *
     * tbhxzm101 HXZ_회원정보
     *
     *
     * [process]
     * 1. HXZ_회원정보 테이블 내의 회원 ID 조회
     * 2. 존재 할 시, 에러 팝업
     *
     * @param req
     * @return
     */
    @PostMapping("/checkId")
    @ResponseBody
    public ResponseEntity<?> readMbrsCountById(
            @Valid @RequestBody CheckIdReqVO req
    ) {
        boolean isCheckId = mbrsJoinService.readMbrsCountById(req.getMbrsId());
        if (isCheckId) {
            return ResponseEntity.ok("아이디가 이미 존재합니다.");
        }
        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }


    /**
     * 회원 가입 화면 조회 step4
     *
     * [process]
     * 1. 회원 가입 화면 step4 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step4.do")
    public String mbrsJoinStep4() {
        return "/hxz/etc/mbrsjoin/step4";
    }
}
