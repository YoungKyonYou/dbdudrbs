package tmoney.co.kr.hxz.common.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {
    public String defaultIfBlank(String s, String def) {
        String t = trim(s);
        return (t == null) ? def : t;
    }
    public String trim(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    public boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    public boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
