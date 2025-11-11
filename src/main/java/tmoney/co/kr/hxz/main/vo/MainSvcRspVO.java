package tmoney.co.kr.hxz.main.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainSvcRspVO {
    private String orgCd;             // 기관코드
    private String tpwSvcId;          // 서비스ID
    private String tpwSvcNm;          // 서비스명
    private String tpwSvcTypId;       // 서비스유형ID
    private String tpwSvcTypNm;       // 서비스유형명
    private String tpwMbrsTypCd;      // 회원유형코드
    private Integer mbrsTypCtgSno;       // 회원유형분류일련번호
    private String tpwMbrsTypCdNm;    // 회원유형코드명
    private String tpwMbrsCtgCd;      // 회원분류코드
    private String mbrsClsNm;         // 회원분류명 ('일반'/'기타')
    private Integer typAplyMinVal;       // 유형적용 최소값
    private Integer typAplyMaxVal;       // 유형적용 최대값
}
