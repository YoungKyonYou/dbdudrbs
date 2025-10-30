package tmoney.co.kr.hxz.etc.mbrsinf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.etc.mbrsinf.service.MbrsInfService;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsInfRspVO;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsUpdReqVO;

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
}


