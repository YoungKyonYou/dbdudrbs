package tmoney.co.kr.hxz.etc.idsrch.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class IdSrchReqVO {
    /** 회원명 */
    @NotNull(message = "회원명은 필수 입력입니다.")
    @Size(max = 100, message = "회원명은 길이가 100이하여야 합니다.")
    private String mbrsNm;
    /** 회원생년월일 */
    @NotNull(message = "회원생년월일은 필수 입력입니다.")
    @Pattern(regexp = "^[0-9]{8}$", message = "생년월일은 8자리 숫자여야 합니다.")
    private String mbrsBrdt;
    /** 회원 휴대폰 번호 */
    @NotNull(message = "휴대전화번호는 필수 입력입니다.")
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "휴대전화번호 형식이 올바르지 않습니다.")
    private String mbrsMbphNo;
}
