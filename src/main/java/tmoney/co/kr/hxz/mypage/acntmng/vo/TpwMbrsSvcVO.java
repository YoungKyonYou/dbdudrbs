package tmoney.co.kr.hxz.mypage.acntmng.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TpwMbrsSvcVO {
    /** 회원ID */
    private String mbrsId;
    /** 회원 서비스 가입일자 */
    private String mbrsSvcJoinDt;
    /** 서비스유형ID */
    private String tpwSvcTypId;
    /** 서비스ID (PK) */
    private String tpwSvcId;
    /** 카드번호 */
    private String cardNo;
    /** 은행코드 */
    private String bnkCd;
    /** 계좌번호 */
    private String acntNo;
    /** 예금주명 */
    private String ooaNm;
    /** 행정동코드 */
    private String addoCd;
    /** 법정동코드 */
    private String stdoCd;
    /** 전입일자(YYYYMMDD) */
    private String mvinDt;
    /** 전출일자(YYYYMMDD) */
    private String mvotDt;
    /** 회원 서비스 상태 코드 */
    private String tpwMbrsSvcStaCd;
    /** 첨부파일 관리번호 */
    private Long atflMngNo;
    /** 회원 서비스 해지일자 */
    private String mbrsSvcCncnDt;

    public TpwMbrsSvcVO(String mbrsId, String mbrsSvcJoinDt, String tpwSvcTypId, String tpwSvcId, String cardNo, String bnkNm, String acntNo, String ooaNm, String stdoCd, String mvinDt, String mvotDt, String tpwMbrsSvcStaCd, Long atflMngNo, String mbrsSvcCncnDt) {
        this.mbrsId = mbrsId;
        this.mbrsSvcJoinDt = mbrsSvcJoinDt;
        this.tpwSvcTypId = tpwSvcTypId;
        this.tpwSvcId = tpwSvcId;
        this.cardNo = cardNo;
        this.bnkCd = bnkNm;
        this.acntNo = acntNo;
        this.ooaNm = ooaNm;
        this.stdoCd = stdoCd;
        this.mvinDt = mvinDt;
        this.mvotDt = mvotDt;
        this.tpwMbrsSvcStaCd = tpwMbrsSvcStaCd;
        this.atflMngNo = atflMngNo;
        this.mbrsSvcCncnDt = mbrsSvcCncnDt;
    }
}
