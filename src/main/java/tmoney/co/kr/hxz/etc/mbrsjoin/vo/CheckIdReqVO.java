package tmoney.co.kr.hxz.etc.mbrsjoin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckIdReqVO {
    /** 회원 ID */
    @NotNull(message = "아이디는 필수 입력입니다.")
    @Pattern(regexp = "^[a-z][a-z0-9_]{5,14}$",
            message = "아이디는 영문 소문자로 시작해야 하고, 영문 소문자, 숫자, 특수문자(_) 만 사용 가능하며 6~15자리여야 합니다.")
    private String mbrsId;
}
