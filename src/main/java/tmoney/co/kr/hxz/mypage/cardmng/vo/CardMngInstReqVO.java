package tmoney.co.kr.hxz.mypage.cardmng.vo;

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
public class CardMngInstReqVO {
    @Size(max = 7, message = "서비스ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;
    @Size(max = 10, message = "서비스 유형 ID는 길이가 10 이하여야 합니다.")
    private String tpwSvcTypId;
    @Size(max = 8, message = "카드시작일자는 길이가 8 이하여야 합니다.")
    private String cardSttDt;
    @Size(max = 8, message = "카드종료일자는 길이가 8 이하여야 합니다.")
    private String cardEndDt;
    @NotNull(message = "카드번호는 필수입니다.")
    @Size(max = 100, message = "카드번호는 길이가 100 이하여야 합니다.")
    private String cardNo;
}
