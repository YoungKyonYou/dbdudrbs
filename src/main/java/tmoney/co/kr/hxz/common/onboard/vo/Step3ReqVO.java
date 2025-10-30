package tmoney.co.kr.hxz.common.onboard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Step3ReqVO {
    private String nonce;
    private Step3VO payload;
}
