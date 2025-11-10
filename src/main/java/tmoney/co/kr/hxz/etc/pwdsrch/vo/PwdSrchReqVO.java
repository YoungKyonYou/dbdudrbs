package tmoney.co.kr.hxz.etc.pwdsrch.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class PwdSrchReqVO {
    /** 회원ID */
    @NotNull(message = "회원명은 필수 입력입니다.")
    @Size(max = 100, message = "회원명은 길이가 100이하여야 합니다.")
    private String mbrsId;
    /** 인증 타입 */
    private String authType;
}
