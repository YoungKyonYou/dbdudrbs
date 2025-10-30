package tmoney.co.kr.hxz.common.onboard.vo;
public class SignupVO {
    private final int step;
    private final String nonce;
    public SignupVO(int step, String nonce) { this.step = step; this.nonce = nonce; }
    public int getStep() { return step; }
    public String getNonce() { return nonce; }
}
