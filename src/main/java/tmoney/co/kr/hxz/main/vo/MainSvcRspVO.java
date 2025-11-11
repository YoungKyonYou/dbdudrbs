package tmoney.co.kr.hxz.main.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainSvcRspVO {
    private String orgCd;             // 기관코드
    private String tpwOrgNm;             // 기관명
    private String tpwSvcId;          // 서비스ID
    private String tpwSvcNm;
    private String joinYn;
}
