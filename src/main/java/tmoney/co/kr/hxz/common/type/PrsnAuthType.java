package tmoney.co.kr.hxz.common.type;

import lombok.Getter;

@Getter
public enum PrsnAuthType {
    TOSS(100, "toss"),
    KAKAO(200, "kakao"),
    PHONE(300, "phone"),
    IPIN(400, "ipin"),

    ;
    private int code;
    private String desc;
    PrsnAuthType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PrsnAuthType fromDesc(String desc) {
        for (PrsnAuthType t : values()) {
            if (t.getDesc().equalsIgnoreCase(desc)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown desc: " + desc);
    }
}
