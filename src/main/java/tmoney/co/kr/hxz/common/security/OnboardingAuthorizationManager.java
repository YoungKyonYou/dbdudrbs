package tmoney.co.kr.hxz.common.security;


import com.github.benmanes.caffeine.cache.Cache;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import tmoney.co.kr.hxz.common.onboard.service.TokenService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;
@Component
@RequiredArgsConstructor
public class OnboardingAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final TokenService tokens;
    private final Cache<String, Boolean> blocklistCache;
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext ctx) {
        HttpServletRequest req = ctx.getRequest();
        String tok = readCookie(req, "onb");
        if (tok == null)
            return new AuthorizationDecision(false);
        JWTClaimsSet c;
        try {
            c = tokens.verify(tok);
        } catch (Exception e) {
            return new AuthorizationDecision(false);
        }
        String onb = (String) c.getClaim("onb");
        String jti = c.getJWTID();
        if (blocklistCache.getIfPresent("onb:" + onb) != null || blocklistCache.getIfPresent("jti:" + jti) != null) {
            return new AuthorizationDecision(false);
        }
        req.setAttribute("claims", c);
        return new AuthorizationDecision(true);
    }
    private static String readCookie(HttpServletRequest req, String name) {
        Cookie[] cs = req.getCookies();
        if (cs == null) return null;
        for (Cookie ck : cs) if (name.equals(ck.getName())) return ck.getValue();
        return null;
    }
}
