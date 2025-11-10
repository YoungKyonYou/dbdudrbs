package tmoney.co.kr.hxz.svcjoin.vo.rsdc;

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
public class RsdcAuthReqVO {
    @NotNull(message = "주민등록번호는 필수 입력입니다.")
    @Size(max = 13, message = "주민등록번호는 길이가 13 이하여야 합니다.")
    private String krn;
    @NotNull(message = "가관코드는 필수 입력입니다.")
    @Size(max = 7, message = "기관코드는 길이가 7 이하여야 합니다.")
    private String orgCd;
    @NotNull(message = "서비스ID는 필수 입력입니다.")
    @Size(max = 7, message = "서비스 ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;
    private String addoCd;
    private String svcRst;
    private String declrDate;
}
