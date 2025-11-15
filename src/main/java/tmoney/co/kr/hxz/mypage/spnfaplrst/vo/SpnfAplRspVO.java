package tmoney.co.kr.hxz.mypage.spnfaplrst.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpnfAplRspVO {
    /** 서비스 ID */
    private String tpwSvcId;
    /** 이름 */
    private String mbrsNm;
    /** 신청일시 */
    private String aplDt;
    /** 승인상태코드 */
    private String aprvStaCd;
}
