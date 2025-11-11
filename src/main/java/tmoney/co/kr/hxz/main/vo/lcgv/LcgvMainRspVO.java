package tmoney.co.kr.hxz.main.vo.lcgv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LcgvMainRspVO {
    private String orgCd;             // 기관코드
    private String tpwOrgNm;             // 기관명
    private String tpwSvcId;          // 서비스ID
    private String tpwSvcNm;
    private String atflPathNm;
    private String stlmDt;
    private String aplAmt;
    private String payAmt;
}
