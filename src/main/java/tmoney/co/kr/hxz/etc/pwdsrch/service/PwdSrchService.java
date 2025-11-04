package tmoney.co.kr.hxz.etc.pwdsrch.service;

import org.springframework.http.ResponseEntity;
import tmoney.co.kr.hxz.etc.pwdsrch.vo.PwdSrchReqVO;

public interface PwdSrchService {
    ResponseEntity<?> findPwd(PwdSrchReqVO req);
}
