package tmoney.co.kr.hxz.news.ntcmttr.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NtcMttrRspVO {
    /** 기관코드 */
    private String orgCd;
    /** 게시번호 */
    private String bltnNo;
    /** 게시구분코드 */
    private String bltnDvsCd;
    /** 교통복지게시등록일시 */
    private String tpwBltnRgtDtm;
    /** 공지사항제목명 */
    private String ntcMttrTtlNm;
    /** 공지사항내용 */
    private String ntcMttrCtt;
    /** 메인노출 여부 */
    private String mainExpsYn;
    /** 메인노출 시작일자 */
    private String mainExpsSttDt;
    /** 메인노출 종료일자 */
    private String mainExpsEndDt;
    /** 조회수 */
    private Integer inqrNcnt;
    /** 사용여부 */
    private String useYn;
    /** 등록일시 */
    private String rgtDtm;
}
