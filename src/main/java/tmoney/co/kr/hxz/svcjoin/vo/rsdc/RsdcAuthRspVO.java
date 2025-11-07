package tmoney.co.kr.hxz.svcjoin.vo.rsdc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RsdcAuthRspVO {
    private String addr;              // 주소
    private String declr_date;        // 전입일
    private String serviceResult;     // 결과코드
    private String resName;
}
