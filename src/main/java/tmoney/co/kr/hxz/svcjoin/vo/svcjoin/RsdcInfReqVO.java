package tmoney.co.kr.hxz.svcjoin.vo.svcjoin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RsdcInfReqVO {
    /** 행정동 코드 */
    @NotNull(message = "행정동 코드는 필수 입력입니다.")
    @Size(max = 10, message = "행정동 코드는 길이가 10 이하여야 합니다.")
    private String addoCd;
    /** 법정동 코드 */
    @Size(max = 10, message = "법정동 코드는 길이가 10 이하여야 합니다.")
    private String stdoCd;
    /** 전입 일자 */
    @Size(max = 8, message = "전입 일자는 길이가 8 이하여야 합니다.")
    private String mvinDt;
}
