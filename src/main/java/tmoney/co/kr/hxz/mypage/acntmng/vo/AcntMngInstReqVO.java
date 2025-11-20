package tmoney.co.kr.hxz.mypage.acntmng.vo;

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
public class AcntMngInstReqVO {
    @Size(max = 7, message = "서비스ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;
    @Size(max = 10, message = "서비스 유형 ID는 길이가 10 이하여야 합니다.")
    private String tpwSvcTypId;
    @NotNull(message = "은행코드는 필수입니다.")
    @Size(max = 100, message = "은행코드는 길이가 100 이하여야 합니다.")
    private String bnkCd;
    @NotNull(message = "계좌번호는 필수입니다.")
    @Size(max = 49, message = "계좌번호는 길이가 49 이하여야 합니다.")
    private String acntNo;
    @NotNull(message = "예금주는 필수입니다.")
    @Size(max = 100, message = "예금주는 길이가 100 이하여야 합니다.")
    private String ooaNm;
}
