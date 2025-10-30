package tmoney.co.kr.hxz.common.onboard.controller;

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
import org.springframework.web.server.ResponseStatusException;
import tmoney.co.kr.hxz.common.onboard.domain.PrecheckContext;
import tmoney.co.kr.hxz.common.onboard.service.OnboardingFlowService;
import tmoney.co.kr.hxz.common.onboard.service.ReceiptService;
import tmoney.co.kr.hxz.common.onboard.service.TokenService;
import tmoney.co.kr.hxz.common.onboard.util.NonceUtil;
import tmoney.co.kr.hxz.common.onboard.util.OnboardingWebUtil;
import tmoney.co.kr.hxz.common.onboard.vo.*;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.Duration;
import java.util.Map;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;


@Controller
@RequestMapping
@Validated
public class SignupController {

    private final NonceUtil nonceUtil;
    private final ReceiptService receiptService;
    private final Cache<String, Boolean> jtiCache;
    private final OnboardingFlowService flow;
    private final Integer onbMaxAgeSeconds;
    private final Integer receiptStepTtlMinutes;

    public SignupController(NonceUtil nonceUtil,
                            ReceiptService receiptService,
                            @Qualifier("jtiCache") Cache<String, Boolean> jtiCache,
                            OnboardingFlowService flow,
                            @Value("${app.onboarding.cookie.onb-max-age-seconds}") Integer onbMaxAgeSeconds,
                            @Value("${app.onboarding.receipt.step-ttl-minutes}") Integer receiptStepTtlMinutes) {
        this.nonceUtil = nonceUtil;
        this.receiptService = receiptService;
        this.jtiCache = jtiCache;
        this.flow = flow;
        this.onbMaxAgeSeconds = onbMaxAgeSeconds;
        this.receiptStepTtlMinutes = receiptStepTtlMinutes;
    }
    // ===== 진입 & 폼 =====

    @GetMapping("/signup/start")
    public String start(HttpServletRequest req, HttpServletResponse res) {
        String onb = OnboardingWebUtil.rand(24);
        String token = flow.issueStartToken(onb);      // step=0, mask=0
        OnboardingWebUtil.writeCookie(req, res, "onb", token, onbMaxAgeSeconds);  // 진행 토큰
        OnboardingWebUtil.expireCookie(req, res, "onb_done");            // 이전 완료 토큰 제거
        return "redirect:/signup";
    }

    @GetMapping("/signup")
    public String form(HttpServletRequest req, Model model) {
        JWTClaimsSet c = (JWTClaimsSet) req.getAttribute("claims");
        int step = ((Number) c.getClaim("step")).intValue();
        String onb = (String) c.getClaim("onb");
        String jti  = c.getJWTID();
        String nonce = nonceUtil.issue(onb, step, jti);
        model.addAttribute("signup", new SignupVO(step, nonce));
        return "signup/form";
    }

    // ===== 단계별 POST: X-Nonce 헤더, 바디=payload =====

    @PostMapping(value="/signup/step/address", consumes="application/json", produces="application/json")
    public ResponseEntity<Map<String,String>> submitAddress(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            @Valid @RequestBody Step1VO payload,
            HttpServletRequest req, HttpServletResponse res) {
        //프론트에서 넘겨준 토큰 및 nonce 체크
        PrecheckContext ctx = flow.precheck(token, nonce, 0);

        //외부연계API 영역 start

//        try {
//
//        }catch(){
//
//        }
        //외부연계API 영역 end


        //다음 step를 위한 토큰 및 nonce, receipt 발급
        String nextToken = flow.advance(req, res, ctx, 1);
        String rcpt = receiptService.issueReceiptFromMap(ctx.getOnb(), 0, payload, Duration.ofMinutes(receiptStepTtlMinutes));
        String nextNonce = flow.issueNonceFor(nextToken);

        return ResponseEntity.ok(Map.of("receipt", rcpt, "nextNonce", nextNonce));
    }

    @PostMapping(value="/signup/step/identity", consumes="application/json", produces="application/json")
    public ResponseEntity<Map<String,String>> submitIdentity(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            @Valid @RequestBody Step2VO payload,
            HttpServletRequest req, HttpServletResponse res) {

        PrecheckContext ctx = flow.precheck(token, nonce, 1);
        String nextToken = flow.advance(req, res, ctx, 2);
        String rcpt = receiptService.issueReceiptFromMap(ctx.getOnb(), 1, payload, Duration.ofMinutes(receiptStepTtlMinutes));
        String nextNonce = flow.issueNonceFor(nextToken);
        return ResponseEntity.ok(Map.of("receipt", rcpt, "nextNonce", nextNonce));
    }

    @PostMapping(value="/signup/step/phone", consumes="application/json", produces="application/json")
    public ResponseEntity<Map<String,String>> submitPhone(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            @Valid @RequestBody Step3VO payload,
            HttpServletRequest req, HttpServletResponse res) {
        PrecheckContext ctx = flow.precheck(token, nonce, 2);
        String nextToken = flow.advance(req, res, ctx, 3);
        String rcpt = receiptService.issueReceiptFromMap(ctx.getOnb(), 2, payload, Duration.ofMinutes(receiptStepTtlMinutes));
        String nextNonce = flow.issueNonceFor(nextToken);

        return ResponseEntity.ok(Map.of("receipt", rcpt, "nextNonce", nextNonce));
    }

    @PostMapping(value="/signup/step/rrn", consumes="application/json", produces="application/json")
    public ResponseEntity<Map<String,String>> submitRrn(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            @Valid @RequestBody Step4VO payload,
            HttpServletRequest req, HttpServletResponse res) {

        PrecheckContext ctx = flow.precheck(token, nonce, 3);
        flow.finishAndIssueDoneToken(req, res, ctx); // onb_done 쿠키 발급
        String rcpt = receiptService.issueReceiptFromMap(ctx.getOnb(), 3, payload, Duration.ofMinutes(receiptStepTtlMinutes));
        return ResponseEntity.ok(Map.of("receipt", rcpt));
    }

    // ===== finalize =====

    @PostMapping(value="/signup/finalize", consumes="application/json")
    public ResponseEntity<Void> finalizeAll(
            @CookieValue("onb_done") String finalToken,
            @Valid @RequestBody FinalizeReqVO body) throws ParseException {

        JWTClaimsSet c = flow.verifyDoneToken(finalToken);
        String onb = (String) c.getClaim("onb");

        receiptService.verifyAndConsumeFromMap(OnboardingWebUtil.reqOr("rcpt1", body.getRcpt1()), onb, 0, body.getStep1());
        receiptService.verifyAndConsumeFromMap(OnboardingWebUtil.reqOr("rcpt2", body.getRcpt2()), onb, 1, body.getStep2());
        receiptService.verifyAndConsumeFromMap(OnboardingWebUtil.reqOr("rcpt3", body.getRcpt3()), onb, 2, body.getStep3());
        receiptService.verifyAndConsumeFromMap(OnboardingWebUtil.reqOr("rcpt4", body.getRcpt4()), onb, 3, body.getStep4());

        // @Transactional 위치 (실제 커밋)
        // userService.commit(onb, body.getStep1(), body.getStep2(), body.getStep3(), body.getStep4());

        return ResponseEntity.noContent().build();
    }

    // ===== 완료 페이지 =====

    @GetMapping("/signup/complete")
    public String complete(
            @CookieValue(value="onb_done", required=false) String finalToken,
            HttpServletRequest req, HttpServletResponse res) {

        if (finalToken == null) {
            throw new ResponseStatusException(FORBIDDEN, "Missing final token");
        }

        JWTClaimsSet c = flow.verifyDoneToken(finalToken); // done=true, step>=4, mask==REQUIRED_MASK
        String jti = c.getJWTID();

        boolean firstUse = jtiCache.asMap().putIfAbsent("final:" + jti, Boolean.TRUE) == null;
        if (!firstUse) throw new ResponseStatusException(CONFLICT, "Final token replay");

        // 렌더 후 쿠키 정리
        OnboardingWebUtil.expireCookie(req, res, "onb_done");
        OnboardingWebUtil.expireCookie(req, res, "onb");
        return "signup/complete";
    }
}
