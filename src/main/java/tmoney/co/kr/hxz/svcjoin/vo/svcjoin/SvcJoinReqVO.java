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
public class SvcJoinReqVO {
    /** 주민등록번호 */
    @NotNull(message = "주민등록번호는 필수 입력입니다.")
    @Size(max = 13, message = "주민등록번호는 길이가 13 이하여야 합니다.")
    private String krn;
    /** 서비스 ID */
    @NotNull(message = "서비스ID는 필수 입력입니다.")
    @Size(max = 7, message = "서비스 ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;
    @NotNull(message = "가관코드는 필수 입력입니다.")
    @Size(max = 7, message = "기관코드는 길이가 7 이하여야 합니다.")
    private String orgCd;
}
