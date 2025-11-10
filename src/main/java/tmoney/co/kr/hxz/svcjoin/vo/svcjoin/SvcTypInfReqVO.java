package tmoney.co.kr.hxz.svcjoin.vo.svcjoin;

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
public class SvcTypInfReqVO {
    /** 서비스 ID */
    @NotNull(message = "서비스ID는 필수 입력입니다.")
    @Size(max = 7, message = "서비스 ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;

    /** 서비스 유형 ID */
    @NotNull(message = "서비스 유형 ID는 필수 입력입니다.")
    @Size(max = 10, message = "서비스 유형 ID은 길이가 10 이하여야 합니다.")
    private String tpwSvcTypId;
}
