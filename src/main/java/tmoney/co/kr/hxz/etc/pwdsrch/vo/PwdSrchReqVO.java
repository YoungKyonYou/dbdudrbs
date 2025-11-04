package tmoney.co.kr.hxz.etc.pwdsrch.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class PwdSrchReqVO {
    private String mbrsId;
    private String authType;
}
