package tmoney.co.kr.hxz.svcjoin.vo.svcjoin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SvcTypInfRspVO {
    /** 기관코드 */
    private String orgCd;
    /** 서비스 ID */
    private String tpwSvcId;

    /** 서비스 유형 ID */
    private String tpwSvcTypId;

    /** 교통복지회원유형코드 */
    private String tpwMbrsTypCd;

    /** 교통복지회원분류코드 */
    private String tpwMbrsCtgCd;

    /** 교통복지회원분류명 */
    private String mbrsClsNm;

    /** 유효적용최소값 */
    private String typAdptMinVal;

    /** 유효적용최대값 */
    private String typAdptMaxVal;
}
