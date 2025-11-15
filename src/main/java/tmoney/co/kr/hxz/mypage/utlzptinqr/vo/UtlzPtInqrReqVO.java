package tmoney.co.kr.hxz.mypage.utlzptinqr.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtlzPtInqrReqVO {
    @NotNull(message = "서비스정보가 없습니다.")
    @Size(max = 7, message = "서비스ID는 7 이하여야 합니다.")
    private String tpwSvcId;
    @Size(max = 8, message = "지급일월의 길이는 8 이하여야 합니다.")
    private String payMon = "20250101";
    @PositiveOrZero(message = "페이지 값은 음수가 될 수 없습니다.")
    private int page = 0;
    @Positive(message = "페이지 값은 0보다 커야 합니다.")
    private int size = 10;
    @Size(max = 14, message = "정렬값의 문자열 길이는 14 이하여야 합니다.")
    private String sort = "pay_dt";
    private String dir = "asc";
}
