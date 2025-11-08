package tmoney.co.kr.hxz.svcjoin.vo.svcjoin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SvcJoinInstReqVO {
    /** 회원 ID */
    private String mbrsId = "tmoney001";

    /** 회원 서비스 가입일 (YYYYMMDD) */
    private String mbrsSvcJoinDt;

    /** 서비스 ID */
    private String tpwSvcId;

    /** 서비스 유형 ID */
    private String tpwSvcTypId;

    /** 카드 번호 */
    private String cardNo;

    /** 은행 코드 */
    private String bnkCd;

    /** 계좌 번호 */
    private String acntNo;

    /** 예금주명 */
    private String ooaNm;

    /** 주소 코드 */
    private String addoCd;

    /** 상세 주소 코드 */
    private String stdoCd;

    /** 전입 일자 (YYYYMMDD) */
    private String mvinDt;

    /** 전출 일자 (YYYYMMDD) */
    private String mvotDt = null;

    /** 회원 서비스 상태 코드 (01: 정상, 02: 해지 등) */
    private String tpwMbrsSvcStaCd = "01";

    /** 첨부파일 관리 번호 */
    private Long atflMngNo;

    /** 회원 서비스 해지 일자 */
    private String mbrsSvcCncnDt;

    /** 등록자 ID */
    private String rgsrId;

    /** 등록 일시 (YYYYMMDDHH24MISS) */
    private String rgtDtm;

    /** 수정자 ID */
    private String updrId;

    /** 수정 일시 (YYYYMMDDHH24MISS) */
    private String updDtm;
}
