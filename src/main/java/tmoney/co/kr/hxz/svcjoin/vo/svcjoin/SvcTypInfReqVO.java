package tmoney.co.kr.hxz.svcjoin.vo.svcjoin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SvcTypInfReqVO {
    /** 서비스 ID */
    private String tpwSvcId;

    /** 서비스 유형 ID */
    private String tpwSvcTypId;
}
