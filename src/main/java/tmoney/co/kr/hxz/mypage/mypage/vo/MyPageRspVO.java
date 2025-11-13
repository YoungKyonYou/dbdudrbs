package tmoney.co.kr.hxz.mypage.mypage.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyPageRspVO {
    /** 서비스 ID */
    private String tpwSvcId;
    /** 기관명(지자체명) */
    private String tpwOrgNm;
    /** 이름 */
    private String mbrsNm;
    /** 카드번호 */
    private String cardNo;
    /** 은행명 */
    private String bnkNm;
    /** 계좌번호 */
    private String acntNo;
    /** 예금주명 */
    private String ooaNm;
}
