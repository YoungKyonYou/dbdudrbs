package tmoney.co.kr.hxz.etc.mbrsjoin.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PrsnAuthReqVO {
    /** 회원명 */
    @Size(max = 100,
            message = "회원명은 길이가 100이하여야 합니다.")
    private String mbrsNm;

    /** 회원 휴대폰 번호 */
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "휴대전화번호 형식이 올바르지 않습니다.")
    private String mbrsMbphNo;

    /** 본인인증CI암호화값 */
    @Size(max = 200, message = "본인인증CI암호화값은 길이가 200이하여야 합니다.")
    private String prsnAuthCiEncVal;

    /** 성별코드 */
    @Size(max = 1, message = "성별코드는 길이가 1 이하여야 합니다.")
    private String gndrCd;

    /** 회원생년월일 */
    @Pattern(regexp = "^[0-9]{8}$", message = "생년월일은 8자리 숫자여야 합니다.")
    private String mbrsBrdt;

    // ✅ JSON 형태로 toString() 오버라이드
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString(); // 실패 시 기본 toString()
        }
    }
}
