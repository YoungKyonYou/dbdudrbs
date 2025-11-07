package tmoney.co.kr.hxz.svcjoin.vo.rsdc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RsdcAuthInstReqVO {
    private String mbrsId;       // 회원ID
    private String orgCd;        // 기관코드
    private String addoCd;       // 행정동 코드
    private String stdoCd;       // 시도 코드
}
