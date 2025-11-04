package tmoney.co.kr.hxz.etc.pwdsrch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.etc.pwdsrch.service.PwdSrchService;
import tmoney.co.kr.hxz.etc.pwdsrch.vo.PwdSrchReqVO;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc/pwdsrch")
public class PwdSrchController {
    private PwdSrchService pwdSrchService;

    /**
     * 비밀 번호 찾기 화면1
     *
     *
     * [process]
     * 1. 아이디 입력
     *
     * @param
     */
    @GetMapping("/pwdSrch.do")
    public String pwdSrch() {
        return "/hxz/etc/pwdsrch/pwdSrch";
    }

    /**
     * 비밀 번호 찾기 화면2
     *
     * @param
     */
    @GetMapping("/pwdSrch2.do")
    public String pwdSrch2(
            @RequestParam("mbrsId") String mbrsId,
            Model model
    ) {
        model.addAttribute("mbrsId", mbrsId);
        return "/hxz/etc/pwdsrch/pwdSrch2";
    }

    /**
     * 비밀 번호 찾기
     * tbhxzm101 HXZ_회원정보
     *
     * [process]
     * 1. 회원ID를 통해서 본인인증 화면 호출
     * 2. 본인인증 완료시, 회원 비밀번호 조회
     *
     *
     * @param
     */
    @PostMapping
    public ResponseEntity<?> findPwd(
            @RequestBody @Valid PwdSrchReqVO req
    ) {
        return pwdSrchService.findPwd(req);
    }
}
