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
public class RsdcAuthInstReqVO {
    /** 서비스 ID */
    @NotNull(message = "서비스ID는 필수 입력입니다.")
    @Size(max = 7, message = "서비스 ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;
    /** 서비스 유형 ID */
    @NotNull(message = "서비스 유형 ID는 필수 입력입니다.")
    @Size(max = 10, message = "서비스 유형 ID은 길이가 10 이하여야 합니다.")
    private String tpwSvcTypId;
    /** 회원 ID */
    @NotNull(message = "회원명은 필수 입력입니다.")
    @Size(max = 20, message = "회원명은 길이가 20이하여야 합니다.")
    private String mbrsId;
    /** 기관코드 */
    @NotNull(message = "기관코드는 필수 입력입니다.")
    @Size(max = 7, message = "기관코드는 길이가 7 이하여야 합니다.")
    private String orgCd;
    /** 행정동 코드 */
    @NotNull(message = "행정동 코드는 필수 입력입니다.")
    @Size(max = 10, message = "행정동 코드는 길이가 10 이하여야 합니다.")
    private String addoCd;
    /** 법정동 코드 */
    @Size(max = 10, message = "법정동 코드는 길이가 10 이하여야 합니다.")
    private String stdoCd;
}
