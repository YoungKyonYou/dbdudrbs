package tmoney.co.kr.hxz.mypage.spfnaplrst.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpfnAplRstReqVO {
    @NotNull(message = "서비스정보가 없습니다.")
    @Size(max = 7, message = "서비스ID는 7 이하여야 합니다.")
    private String tpwSvcId;
}
