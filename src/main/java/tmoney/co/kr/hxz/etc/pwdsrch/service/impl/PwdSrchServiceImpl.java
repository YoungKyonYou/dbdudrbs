package tmoney.co.kr.hxz.etc.pwdsrch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.etc.pwdsrch.service.PwdSrchService;
import tmoney.co.kr.hxz.etc.pwdsrch.vo.PwdSrchReqVO;

@RequiredArgsConstructor
@Service
public class PwdSrchServiceImpl implements PwdSrchService {

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findPwd(PwdSrchReqVO req) {

        try {
            boolean success = performAuth(req.getAuthType(), req.getMbrsId());

            if (success) {
                return ResponseEntity.ok(req.getAuthType().toUpperCase() + " 인증 성공!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(req.getAuthType().toUpperCase() + " 인증 실패. 다시 시도해주세요.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("인증 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    };

    public boolean performAuth(String type, String mbrsId) {
        switch (type.toLowerCase()) {
            case "phone":
                return simulateAuth(true);  // 임시 성공
            case "ipin":
                return simulateAuth(false); // 임시 실패
            default:
                throw new IllegalArgumentException("지원하지 않는 인증 방식: " + type);
        }
    }

    private boolean simulateAuth(boolean success) {
        return success;
    }
}
