package tmoney.co.kr.hxz.news.faq.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FaqSrchReqVO {
    /** FAQ제목명 */
    private String faqTtlNm;
    /** 사용여부 */
    private String useYn = "Y";
    @PositiveOrZero(message = "페이지 값은 음수가 될 수 없습니다.")
    private int page = 0;
    @Positive(message = "페이지 값은 0보다 커야 합니다.")
    private int size = 10;
    @Size(max = 15, message = "")
    private String sort = "rgt_dt";

    private String dir = "asc";
}
