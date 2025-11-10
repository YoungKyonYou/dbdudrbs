package tmoney.co.kr.hxz.etc.mbrsinf.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MbrsUpdReqVO {
    /** 회원ID */
    @NotNull(message = "아이디는 필수 입력입니다.")
    @Pattern(regexp = "^[a-z][a-z0-9_]{5,14}$",
            message = "아이디는 영문 소문자로 시작해야 하고, 영문 소문자, 숫자, 특수문자(_) 만 사용 가능하며 6~15자리여야 합니다.")
    private String mbrsId;
    /** 회원명 */
    @Size(max = 100, message = "회원명은 길이가 100이하여야 합니다.")
    private String mbrsNm;
    /** 이메일주소 */
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 64, message = "이메일은 길이가 64이하여야 합니다.")
    private String mailAddr;
    /** 회원 휴대폰 번호 */
    @NotNull(message = "휴대전화번호는 필수입니다.")
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "휴대전화번호 형식이 올바르지 않습니다.")
    private String mbrsMbphNo;
    /** 회원 전화번호 */
    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 입력 가능합니다.")
    @Size(max = 20, message = "전화번호는 길이가 20이하여야 합니다.")
    private String mbrsTelNo;
    /** 회원 상태 코드 */
    @Size(max = 2, message = "회원상태코드는 길이가 2이하여야 합니다.")
    private String mbrsStaCd;
    /** 본인인증CI값 */
    @NotNull(message = "본인 인증 CI 암호값은 필수입니다.")
    @Size(max = 200, message = "본인인증CI암호화값은 길이가 200이하여야 합니다.")
    private String prsnAuthCiEncVal;
    /** 성별 코드 */
    @NotNull(message = "성별 코드는 필수입니다.")
    @Size(max = 1, message = "성별코드는 길이가 1 이하여야 합니다.")
    private String gndrCd;
    /** 회원생년월일 */
    @NotNull(message = "회원생년월일은 필수입니다.")
    @Pattern(regexp = "^[0-9]{8}$", message = "생년월일은 8자리 숫자여야 합니다.")
    private String mbrsBrdt;
}
