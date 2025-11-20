package tmoney.co.kr.hxz.mypage.acntmng.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcntMngRspVO {
    private String acntRgtDt;
    private String bnkNm;
    private String acntNo;
    private String prevBnkNm;
    private String prevAcntNo;
}
