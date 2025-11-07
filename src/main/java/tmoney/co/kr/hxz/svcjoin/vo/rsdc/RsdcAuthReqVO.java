package tmoney.co.kr.hxz.svcjoin.vo.rsdc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RsdcAuthReqVO {
    private String krn;
    private String orgCd;
    private String tpwSvcId;
}
