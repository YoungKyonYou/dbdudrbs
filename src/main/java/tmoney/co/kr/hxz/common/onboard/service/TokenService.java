package tmoney.co.kr.hxz.common.onboard.service;

import com.nimbusds.jwt.JWTClaimsSet;

import java.time.Duration;

public interface TokenService {
    String issue(String onb, int step, int mask, Duration ttl);
    String issueFinal(String onb, int finalStep, int mask, boolean done, Duration ttl);
    JWTClaimsSet verify(String token);
    String rand(int bytes);
}
