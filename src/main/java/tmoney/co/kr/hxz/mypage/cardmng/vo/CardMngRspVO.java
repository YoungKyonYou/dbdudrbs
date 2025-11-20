package tmoney.co.kr.hxz.mypage.cardmng.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardMngRspVO {
    private String cardModDt;
    private String cardNo;
    private String prevCardNo;
}
