package tmoney.co.kr.hxz.svcjoin.vo.svccncn;

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
public class SvcCncnReqVO {
    @NotNull(message = "서비스ID는 필수 입력입니다.")
    @Size(max = 7, message = "서비스 ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId = "SVC010";
}
