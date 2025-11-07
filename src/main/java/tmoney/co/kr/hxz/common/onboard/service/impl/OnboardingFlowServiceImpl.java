package tmoney.co.kr.hxz.common.onboard.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tmoney.co.kr.hxz.common.onboard.domain.PrecheckContext;
import tmoney.co.kr.hxz.common.onboard.service.OnboardingFlowService;
import tmoney.co.kr.hxz.common.onboard.service.TokenService;
import tmoney.co.kr.hxz.common.onboard.util.NonceUtil;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static tmoney.co.kr.hxz.common.onboard.util.OnboardingWebUtil.writeCookie;

@Service
public class OnboardingFlowServiceImpl implements OnboardingFlowService {

    private final TokenService tokens;
    private final NonceUtil nonceUtil;
    private final Cache<String, Boolean> jtiCache;
    private final int doneTokenTtlSeconds;

    private final long tokTtlMin;

    public OnboardingFlowServiceImpl(TokenService tokens,
                                     NonceUtil nonceUtil,
                                     @Qualifier("jtiCache")  Cache<String, Boolean> jtiCache,
                                     @Value("${app.onboarding.done-token-ttl-seconds}") int doneTokenTtlSeconds,
                                     @Value("${app.onboarding.token-ttl-minutes}") long tokTtlMin) {
        this.tokens = tokens;
        this.nonceUtil = nonceUtil;
        this.jtiCache = jtiCache;
        this.doneTokenTtlSeconds = doneTokenTtlSeconds;
        this.tokTtlMin = tokTtlMin;
    }

    /** 4단계(0~3) 전체 완료 마스크 0b1111 */
    public static final int REQUIRED_MASK = (1 << 2) - 1;

    /** 시작 토큰 발급 */
    @Override
    public String issueStartToken(String onb) {
        return tokens.issue(onb, 0, 0, Duration.ofMinutes(tokTtlMin));
    }

    /** 토큰/nonce/step 일치 사전 검증 */
    @Override
    public PrecheckContext precheck(String token, String nonce, int expectedStep) {
        JWTClaimsSet c = tokens.verify(token);

        int step = ((Number) c.getClaim("step")).intValue();
        if (step != expectedStep)
            throw DomainExceptionCode.SIGNUP_PRECHECK_ERROR.newInstance("인증에 오류가 발생했습니다. 처음부터 다시 진행해주세요.");


        String onb = (String) c.getClaim("onb");
        String jti  = c.getJWTID();

        if (!nonceUtil.verify(nonce, onb, step, jti))
            throw DomainExceptionCode.SIGNUP_PRECHECK_ERROR.newInstance("접근토큰에 오류가 발생했습니다. 처음부터 다시 진행해주세요.");

        int mask = ((Number) c.getClaim("mask")).intValue();
        return new PrecheckContext(onb, jti, step, mask);
    }

    /** 다음 단계 토큰 발급 + 쿠키 갱신, next 토큰 문자열 반환 */
    @Override
    public String advance(HttpServletRequest req, HttpServletResponse res, PrecheckContext ctx, int nextStep) {
        boolean firstUse = jtiCache.asMap().putIfAbsent("jti:" + ctx.getJti(), Boolean.TRUE) == null;
        if (!firstUse)
            throw DomainExceptionCode.SIGNUP_JTI_REPLAY_ERROR.newInstance("인증 토큰이 재사용되었습니다. 처음부터 다시 진행해주세요.");

        int newMask = ctx.getMask() | (1 << ctx.getStep());
        String next = tokens.issue(ctx.getOnb(), nextStep, newMask, Duration.ofMinutes(tokTtlMin));
        writeCookie(req, res, "onb", next, 60 * 60);
        return next;
    }

    /** 주어진 토큰에서 현재 단계용 nonce 발급 */
    @Override
    public String issueNonceFor(String token) {
        JWTClaimsSet c = tokens.verify(token);
        String onb = (String) c.getClaim("onb");
        int step = ((Number) c.getClaim("step")).intValue();
        String jti = c.getJWTID();
        return nonceUtil.issue(onb, step, jti);
    }

    /** 마지막 단계 완료 처리(onb_done 발급) */
    @Override
    public String finishAndIssueDoneToken(HttpServletRequest req, HttpServletResponse res, PrecheckContext ctx) {
        boolean firstUse = jtiCache.asMap().putIfAbsent("jti:" + ctx.getJti(), Boolean.TRUE) == null;
        if (!firstUse)
            throw DomainExceptionCode.SIGNUP_JTI_REPLAY_ERROR.newInstance("인증 토큰이 재사용되었습니다. 처음부터 다시 진행해주세요.");

        int newMask = ctx.getMask() | (1 << ctx.getStep());
        if (newMask != REQUIRED_MASK)
            throw DomainExceptionCode.SIGNUP_NOT_ALL_STEPS_FINISHED.newInstance("인증 절차가 올바르지 않습니다. 처음부터 다시 진행해주세요.");

        String finalToken = tokens.issueFinal(ctx.getOnb(), 4, newMask, true, Duration.ofSeconds(doneTokenTtlSeconds));
        writeCookie(req, res, "onb_done", finalToken, doneTokenTtlSeconds);
        return finalToken;
    }

    /** 완료 토큰 검증(complete/finalize 공용) */
    @Override
    public JWTClaimsSet verifyDoneToken(String finalToken) {
        JWTClaimsSet c = tokens.verify(finalToken);
        boolean done = booleanClaim(c, "done");
        int step = ((Number) c.getClaim("step")).intValue();
        int mask = ((Number) c.getClaim("mask")).intValue();
        if (!done || step < 4 || mask != REQUIRED_MASK) {
            throw DomainExceptionCode.SIGNUP_EXCEPTION.newInstance("인증에 오류가 발생했습니다. 처음부터 다시 진행해주세요.");
        }
        return c;
    }

    /** boolean claim helper (서비스 내 편의용) */
    private boolean booleanClaim(JWTClaimsSet c, String name) {
        Object v = c.getClaim(name);
        if (v instanceof Boolean) return (Boolean) v;
        if (v instanceof String)  return Boolean.parseBoolean((String) v);
        return false;
    }
}
