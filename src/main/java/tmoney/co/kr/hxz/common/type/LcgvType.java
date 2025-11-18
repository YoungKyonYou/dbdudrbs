package tmoney.co.kr.hxz.common.type;

import lombok.Getter;

@Getter
public enum LcgvType {
    JONGRO(100, "/skin/jongro/page/index"),
    JOONGGU(200, "/skin/jonggu/page/index"),
    ;
    private int code;
    private String name;
    LcgvType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
