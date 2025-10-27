package tmoney.co.kr.hxz.news.ntcmttr.vo;

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
public class SrchReqVO {
    /** 기관코드 */
    private String orgCd;
    /** 공지사항제목명 */
    private String ntcMttrTtlNm;
    /** 공지사항내용 */
    private String ntcMttrCtt;
    @PositiveOrZero(message = "페이지 값은 음수가 될 수 없습니다.")
    private int page = 0;
    @Positive(message = "페이지 값은 0보다 커야 합니다.")
    private int size = 10;
    @Size(max = 15, message = "")
    private String sort = "ntc_mttr_ttl_nm";

    private String dir = "asc";
}
