package tmoney.co.kr.hxz.news.faq.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FaqRspVO {
    /** 게시번호 */
    private String bltnNo;
    /** FAQ제목명 */
    private String faqTtlNm;
    /** FAQ내용 */
    private String faqCtt;
    /** 답변내용 */
    private String answCtt;
    /** 등록일자 */
    private String rgtDt;
    /** 조회건수 */
    private Long inqrNcnt;
    /** 사용여부 */
    private String useYn;
    /** 등록자ID */
    private String rgsrId;
    /** 등록일시 */
    private String rgtDtm;
    /** 수정자ID */
    private String updrId;
    /** 수정일시 */
    private String updDtm;
}
