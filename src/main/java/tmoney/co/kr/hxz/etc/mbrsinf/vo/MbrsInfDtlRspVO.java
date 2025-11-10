package tmoney.co.kr.hxz.etc.mbrsinf.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MbrsInfDtlRspVO {
    /** 회원 ID */
    private String mbrsId;
    /** 회원명 */
    private String mbrsNm;
    /** 이메일 주소 */
    private String mailAddr;
    /** 회원 휴대폰 번호 */
    private String mbrsMbphNo;
    /** 회원 전화번호 */
    private String mbrsTelNo;
    /** 생년월일 */
    private String mbrsBrdt;
}
