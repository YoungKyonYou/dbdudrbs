package tmoney.co.kr.hxz.common.onboard.service;

import com.nimbusds.jwt.JWTClaimsSet;
import tmoney.co.kr.hxz.common.onboard.domain.PrecheckContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OnboardingFlowService {
    String issueStartToken(String onb);
    PrecheckContext precheck(String token, String nonce, int expectedStep);
    String advance(HttpServletRequest req, HttpServletResponse res, PrecheckContext ctx, int nextStep);
    String issueNonceFor(String token);
    String finishAndIssueDoneToken(HttpServletRequest req, HttpServletResponse res, PrecheckContext ctx);
    JWTClaimsSet verifyDoneToken(String finalToken);

}
