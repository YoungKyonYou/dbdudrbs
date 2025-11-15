package tmoney.co.kr.hxz.mypage.spnfptinqr.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpnfPtInqrReqVO {
    private String tpwSvcId;
    private String paySttDt;
    private String payEndDt;

    private int page = 0;
    private int size = 10;
    private String sort = "pay_dt";
    private String dir = "asc";
}
