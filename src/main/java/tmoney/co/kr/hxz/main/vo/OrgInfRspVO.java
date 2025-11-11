package tmoney.co.kr.hxz.main.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrgInfRspVO {
    private String orgCd;             // 기관코드
    private String tpwOrgNm;             // 기관명
    private String tpwSvcId;
    private String tpwSvcNm;
}
