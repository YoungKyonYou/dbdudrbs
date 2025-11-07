package tmoney.co.kr.hxz.svcjoin.vo.prevsvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrevSvcRspVO {
    private String orgCd;
    private String tpwSvcId;
    private String tpwSvcNm;
    private String tpwSvcTypId;
    private String tpwSvcTypNm;
    private String sprtDplcYn;
}
