package tmoney.co.kr.hxz.mypage.spnfaplrst.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpnfAplRstReqVO {
    @Size(max = 7, message = "서비스ID는 7 이하여야 합니다.")
    private String tpwSvcId;
}
