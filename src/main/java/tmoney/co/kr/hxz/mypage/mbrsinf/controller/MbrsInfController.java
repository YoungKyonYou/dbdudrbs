package tmoney.co.kr.hxz.mypage.mbrsinf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.mypage.mbrsinf.service.MbrsInfService;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;

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
    @GetMapping("/{mbrsId}")
    public String readMbrsInf(
            @PathVariable("mbrsId") String mbrsId,
            Model model
    ) {
        MbrsInfRspVO mbrsInf = mbrsInfService.readMbrsInf(mbrsId);
        model.addAttribute("mbrsInf", mbrsInf);
        return "/hxz/mypage/mbrsinf/mbrsInf.html";
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
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateMbrsInf(
            @Valid @RequestBody MbrsInfReqVO req
    ) {
        mbrsInfService.updateMbrsInf(req);
        return ResponseEntity.ok().build();
    }
}


