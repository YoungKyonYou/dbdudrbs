package tmoney.co.kr.hxz.news.ntcmttr.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RspVO {
    private String orgCd;           // 기관코드
    private String bltnNo;          // 게시번호
    private String bltnDvsCd;       // 게시구분코드
    private String tpwBltnRgtDtm;   // 교통복지게시등록일시
    private String ntcMtrrTtlNm;    // 공지사항제목명
    private String ntcMtrrCtt;      // 공지사항내용
    private String mainExpsYn;      // 메인노출 여부
    private String mainExpsSttDt;   // 메인노출 시작일자
    private String mainExpsEndDt;   // 메인노출 종료일자
    private Integer inqrNcnt;       // 조회수
    private String useYn;           // 사용여부
    private String rgtDtm;          // 등록일시
}
