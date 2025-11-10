package tmoney.co.kr.hxz.svcjoin.vo.svcjoin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SvcJoinInstReqVO {
    /** 주민등록번호 */
    @NotNull(message = "주민등록번호는 필수 입력입니다.")
    @Size(max = 13, message = "주민등록번호는 길이가 13 이하여야 합니다.")
    private String krn;

    /** 회원 Id */
    @NotNull(message = "회원ID은 필수 입력입니다.")
    @Size(max = 20, message = "회원ID은 길이가 20이하여야 합니다.")
    private String mbrsId = "tmoney001";

    /** 회원 서비스 가입일 (YYYYMMDD) */
    @NotNull(message = "회원 서비스 가입 일자는 필수 입력입니다.")
    @Size(max = 8, message = "회원 서비스 가입 일자는 길이가 8이하여야 합니다.")
    private String mbrsSvcJoinDt;

    /** 서비스 ID */
    @NotNull(message = "서비스ID는 필수 입력입니다.")
    @Size(max = 7, message = "서비스 ID는 길이가 7 이하여야 합니다.")
    private String tpwSvcId;

    /** 서비스 유형 ID */
    @NotNull(message = "서비스 유형 ID는 필수 입력입니다.")
    @Size(max = 10, message = "서비스 유형 ID은 길이가 10 이하여야 합니다.")
    private String tpwSvcTypId;

    /** 카드 번호 */
    @NotNull(message = "카드 번호는 필수 입력입니다.")
    @Size(max = 100, message = "카드 번호는 길이가 100 이하여야 합니다.")
    private String cardNo;

    /** 은행 코드 */
    @NotNull(message = "은행 코드는 필수 입력입니다.")
    @Size(max = 3, message = "은행 코드는 길이가 3 이하여야 합니다.")
    private String bnkCd;

    /** 계좌 번호 */
    @NotNull(message = "계좌 번호는 필수 입력입니다.")
    @Size(max = 49, message = "계좌 번호는 길이가 49 이하여야 합니다.")
    private String acntNo;

    /** 예금주명 */
    @NotNull(message = "예금주명은 필수 입력입니다.")
    @Size(max = 3, message = "예금주명은 길이가 100 이하여야 합니다.")
    private String ooaNm;

    /** 회원 서비스 상태 코드 (01: 정상, 02: 해지 등) */
    @NotNull(message = "예금주명은 필수 입력입니다.")
    @Size(max = 3, message = "예금주명은 길이가 100 이하여야 합니다.")
    private String tpwMbrsSvcStaCd = "01";

    /** 첨부파일 관리 번호 */
    @Digits(integer = 10, fraction = 0, message = "첨부파일 관리 번호는 길이가 10 이하여야 합니다.")
    private Long atflMngNo;

    /** 회원 서비스 해지 일자 */
    @Size(max = 8, message = "회원 서비스 해지 일자는 길이가 100 이하여야 합니다.")
    private String mbrsSvcCncnDt;
}
