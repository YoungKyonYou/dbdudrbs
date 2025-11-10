package tmoney.co.kr.hxz.etc.mbrsinf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;
import tmoney.co.kr.hxz.etc.mbrsinf.service.MbrsInfService;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsInfDtlRspVO;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsInfRspVO;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsUpdReqVO;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc/mbrsinf")
public class MbrsInfController {
    private final MbrsInfService mbrsInfService;

    /**
     * 회원 정보 상세 조회
     * tbhxzm101 HXZ_회원정보
     *
     * [process]
     * 1. 회원ID(mbrsId)를 통해서 회원 정보 상세 조회
     * 2. 로그인을 한 상태이므로, 회원상태코드(휴면, 탈퇴)에 대한 예외처리하지 않음
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/mbrsInf.do")
    public String mbrsInf(
            HttpSession httpSession,
            Model model
    ) {
        String mbrsId = (String) httpSession.getAttribute("mbrsId");
        if (mbrsId == null) {
            throw DomainExceptionCode.LOGIN_ID_NOT_FOUND.newInstance();
        }

        MbrsInfDtlRspVO mbrsInf = mbrsInfService.mbrsInf(mbrsId);
        model.addAttribute("mbrsInf", mbrsInf);
        return "/hxz/etc/mbrsinf/mbrsInf";
    }

    /**
     * 회원 정보 수정
     *
     * tbhxzm101 HXZ_회원정보
     * tbhxzh101 HXZ_회원정보변경이력
     *
     * [process]
     * 1. HXZ_회원정보 테이블 내의 회원 정보 수정
     * 2. HXZ_회원정보변경이력 테이블 회원정보 변경 이력 추가
     *
     * @param req
     * @return
     */
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> updateMbrsInf(
            @RequestBody @Valid MbrsUpdReqVO req,
            HttpSession httpSession
    ) {
        String mbrsId = (String) httpSession.getAttribute("mbrsId");
        if (mbrsId == null) {
            throw DomainExceptionCode.LOGIN_ID_NOT_FOUND.newInstance();
        }

        mbrsInfService.updateMbrsInf(req, mbrsId);
        return ResponseEntity.ok().build();
    }

    /**
     * 비밀번호 변경 페이지
     *
     * @return
     */
    @GetMapping("/pwdForm.do")
    public String pwdForm(
            HttpSession httpSession,
            Model model
    ) {
        String mbrsId = (String) httpSession.getAttribute("mbrsId");
        if (mbrsId == null) {
            throw DomainExceptionCode.LOGIN_ID_NOT_FOUND.newInstance();
        }

        MbrsInfDtlRspVO mbrsInf = mbrsInfService.mbrsInf(mbrsId);

        model.addAttribute("mbrsInf", mbrsInf);
        model.addAttribute("mbrsId", mbrsId);
        return "/hxz/etc/mbrsinf/pwdForm";
    }

    /**
     * 비밀번호 변경 처리
     */
    @PostMapping("/pwd")
    @ResponseBody
    public ResponseEntity<?> updatePwd(
            @RequestParam String newPwd,
            @RequestParam String cfmPwd,
            HttpSession httpSession
    ) {
        String mbrsId = (String) httpSession.getAttribute("mbrsId");
        if (mbrsId == null) {
            throw DomainExceptionCode.LOGIN_ID_NOT_FOUND.newInstance();
        }

        mbrsInfService.updatePwd(mbrsId, newPwd, cfmPwd);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 탈퇴 페이지
     *
     * @return
     */
    @GetMapping("/mbrsScsn.do")
    public String mbrsScsn() {
        return "/hxz/etc/mbrsinf/mbrsScsn";
    }
}


