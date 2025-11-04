package tmoney.co.kr.hxz.common.onboard.filter;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.web.filter.OncePerRequestFilter;
import tmoney.co.kr.hxz.common.onboard.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OnboardingClaimsFilter extends OncePerRequestFilter {
    private final TokenService tokens;

    public OnboardingClaimsFilter(TokenService tokens) {
        this.tokens = tokens;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        // (A) /signup/complete 는 onb_done 만 확인하고 통과 (onb 없어도 OK)
        if ("/etc/mbrsjoin/step4.do".equals(path)) {
            String done = readCookie(req, "onb_done");
            if (done != null) {
                try {
                    JWTClaimsSet c = tokens.verify(done);
                    // 필요시 req.setAttribute("claims", c); // 컨트롤러에서 안 쓰면 생략 가능
                    chain.doFilter(req, res);
                    return;
                } catch (Exception ignore) { /* 아래 로직으로 폴백 */ }
            }
            // onb_done이 유효하지 않으면 기존 정책으로
        }

        // (B) 그 외 /signup/** 는 기존대로 onb 쿠키 검증
        if (path.startsWith("/etc/mbrsjoin")) {
            String tok = readCookie(req, "onb");
            if (tok == null) {
                res.sendRedirect("/etc/mbrsjoin/start");
                return;
            }
            try {
                JWTClaimsSet claims = tokens.verify(tok);
                req.setAttribute("claims", claims);
            } catch (Exception e) {
                res.sendRedirect("/etc/mbrsjoin/start");
                return;
            }
        }

        chain.doFilter(req, res);
    }

    private static String readCookie(HttpServletRequest req, String name) {
        if (req.getCookies() == null) return null;
        for (Cookie c : req.getCookies()) if (name.equals(c.getName())) return c.getValue();
        return null;
    }
}
