package tmoney.co.kr.hxz.mypage.mbrsinf.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MbrsInfRspVO {
    /** 회원ID */
    private String mbrsId;
    /** 회원명 */
    private String mbrsNm;
    /** 이메일주소 */
    private String mailAddr;
    /** 회원 휴대폰 번호 */
    private String mbrsMbphNo;
    /** 회원 전화번호 */
    private String mbrsTelNo;
    /** 비밀번호 */
    private String pwd;
    /** 회원 상태 코드 */
    private String mbrsStaCd;
    /** 본인인증CI값 */
    private String prsnAuthCiEncVal;
    /** 회원생년월일 */
    private String mbrsBrdt;
}
