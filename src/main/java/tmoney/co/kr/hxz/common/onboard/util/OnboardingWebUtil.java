package tmoney.co.kr.hxz.common.onboard.util;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Base64;

public final class OnboardingWebUtil {

    private OnboardingWebUtil() {}

    public static void writeCookie(HttpServletRequest req, HttpServletResponse res,
                                   String name, String value, int maxAgeSeconds) {
        boolean secure = req.isSecure();
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("=").append(value)
                .append("; Max-Age=").append(maxAgeSeconds)
                .append("; Path=/")
                .append("; HttpOnly");
        if (secure) sb.append("; Secure");
        sb.append("; SameSite=Lax");
        res.addHeader("Set-Cookie", sb.toString());
    }

    public static void expireCookie(HttpServletRequest req, HttpServletResponse res, String name) {
        boolean secure = req.isSecure();
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("=; Max-Age=0; Path=/; HttpOnly");
        if (secure) sb.append("; Secure");
        sb.append("; SameSite=Lax");
        res.addHeader("Set-Cookie", sb.toString());
    }

    public static String reqOr(String name, String v) {
        if (v == null || v.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, name + " is required");
        return v;
    }

    public static boolean booleanClaim(JWTClaimsSet c, String name) {
        Object v = c.getClaim(name);
        if (v instanceof Boolean) return (Boolean) v;
        if (v instanceof String) return Boolean.parseBoolean((String) v);
        return false;
    }

    public static String rand(int bytes) {
        byte[] b = new byte[bytes];
        new SecureRandom().nextBytes(b);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(b);
    }
}
