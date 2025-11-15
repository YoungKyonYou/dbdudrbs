package tmoney.co.kr.hxz.lcgvmain.vo;

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
public class LcgvMainReqVO {
    @NotNull(message = "기관정보가 없습니다.")
    @Size(max = 7, message = "기관코드는 길이가 7 이하여야 합니다.")
    private String orgCd;
    @NotNull(message = "서비스정보가 없습니다.")
    @Size(max = 7, message = "서비스 ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;
}
