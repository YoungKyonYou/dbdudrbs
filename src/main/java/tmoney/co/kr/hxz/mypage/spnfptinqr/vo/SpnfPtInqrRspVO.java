package tmoney.co.kr.hxz.mypage.spnfptinqr.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpnfPtInqrRspVO {
    /** 서비스ID */
    private String tpwSvcId;
    /** 지급년월 */
    private String payDt;
    /** 은행명 */
    private String bnkNm;
    /** 계좌번호 */
    private String acntNo;
    /** 지급금액 */
    private String payAmt;
}
