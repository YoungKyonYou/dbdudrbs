package tmoney.co.kr.hxz.mypage.utlzptinqr.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtlzPtInqrRspVO {
    /** 서비스ID */
    private String tpwSvcId;
    /** 지급년월 */
    private String stlmDt;
    /** 이용일자 */
    private String trdDt;
    /** 정산금액 */
    private String trdAmt;

}
