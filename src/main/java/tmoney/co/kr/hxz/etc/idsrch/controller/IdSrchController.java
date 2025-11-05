package tmoney.co.kr.hxz.etc.idsrch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.etc.idsrch.service.IdSrchService;
import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchReqVO;
import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchRspVO;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc/idsrch")
public class IdSrchController {
    private final IdSrchService idSrchService;

    /**
     * 아이디 찾기 화면
     * tbhxzm101 HXZ_회원정보
     *
     * [process]
     * 1. 아이디 찾기 화면 이동
     *
     * @param
     */
    @GetMapping("/idSrch.do")
    public String idSrch() {
        return "/hxz/etc/idsrch/idSrch";
    }

    /**
     * 아이디 찾기
     * tbhxzm101 HXZ_회원정보
     *
     * [process]
     * 1. 이름, 생년월일, 휴대폰 번호를 통해서 회원ID 조회
     * 2. 로그인을 안 한 상태이므로, 회원상태코드(휴면, 탈퇴)에 대한 예외처리하지 않음
     *
     * @param
     */
    @GetMapping("/idSrch2.do")
    public String findMbrsId(
            @ModelAttribute @Valid IdSrchReqVO req,
            Model model
    ) {
        IdSrchRspVO result = idSrchService.findMbrsId(req);

        model.addAttribute("result", result);
        return "/hxz/etc/idsrch/idSrch2";
    }
}
