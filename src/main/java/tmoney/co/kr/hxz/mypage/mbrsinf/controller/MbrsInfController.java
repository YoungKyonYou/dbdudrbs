package tmoney.co.kr.hxz.mypage.mbrsinf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;
import tmoney.co.kr.hxz.mypage.mbrsinf.service.MbrsInfService;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsUpdReqVO;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/mbrsinf")
public class MbrsInfController {
    private final MbrsInfService mbrsInfService;

    /**
     * 회원 정보 상세 조회
     * tbhxzm101 HXZ_회원정보
     *
     * [process]
     * 1. 회원ID(mbrsId)를 통해서 회원 정보 상세 조회
     *
     * @param mbrsId
     * @param model
     * @return
     */
    @GetMapping("/{mbrsId}/mbrsInf.do")
    public String readMbrsInf(
            @PathVariable("mbrsId") String mbrsId,
            Model model
    ) {
        MbrsInfRspVO mbrsInf = mbrsInfService.readMbrsInf(mbrsId);
        model.addAttribute("mbrsInf", mbrsInf);
        return "/hxz/mypage/mbrsinf/mbrsInf";
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
            @Valid @RequestBody MbrsUpdReqVO req
    ) {
        mbrsInfService.updateMbrsInf(req);
        return ResponseEntity.ok().build();
    }

    /**
     * 비밀번호 변경 페이지
     *
     * @return
     */
    @GetMapping("/{mbrsId}/pwdForm.do")
    public String pwdForm(
        @PathVariable("mbrsId") String mbrsId,
        Model model
    ) {
        model.addAttribute("mbrsId", mbrsId);
        return "/hxz/mypage/mbrsinf/pwdForm";
    }

    /**
     * 비밀번호 변경 처리
     */
    @PostMapping("/pwd/{mbrsId}")
    @ResponseBody
    public ResponseEntity<?> updatePwd(
            @RequestParam String newPwd,
            @RequestParam String cfmPwd,
            @PathVariable("mbrsId") String mbrsId
    ) {
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
        return "/hxz/mypage/mbrsinf/mbrsScsn";
    }
}


