package tmoney.co.kr.hxz.etc.idsrch.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IdSrchRspVO {
    /** 회원 ID */
    private String mbrsId;
    /** 등록일시 */
    private String rgtDtm;  // 가입일시
}
