package tmoney.co.kr.hxz.mypage.acntmng.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.acntmng.service.AcntMngService;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngInstReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngRspVO;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class AcntMngController {
    private final AcntMngService acntMngService;
    /**
     * 카드 관리 조회
     * tbhxzh105 계좌변경이력
     *
     * [process]
     * 1. 현재 가입한 서비스에서 계좌 변경 이력 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/acntMng.do")
    public String readAcntMngPaging(
            @ModelAttribute @Valid AcntMngReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        PageData<AcntMngRspVO> contents = acntMngService.readAcntMngPaging(req, mbrsId);
        AcntMngRspVO result = acntMngService.readPrsAcntMng(req, mbrsId);

        model.addAttribute("pageData", contents);
        model.addAttribute("result", result);
        return "/hxz/mypage/acntmng/acntMng";
    }

    /**
     * 계좌 변경 등록
     * tbhxzh105 계좌변경이력
     * tbhxzm102 회원서비스정보
     * tbhxzh102 회원서비스정보이력
     *
     * [process]
     * 1. 현재 가입한 회원서비스의 계좌변경이력 추가
     * 1. 회원서비스정보의 계좌 정보 update
     * 2. 회원서비스정보이력 추가
     * 3.
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/acntMng.do")
    public ResponseEntity<?> acntMng(
            @ModelAttribute @Valid AcntMngInstReqVO req
    ) {
        String mbrsId = "tmoney002";
        acntMngService.acntMng(req, mbrsId);

        return ResponseEntity.ok().build();
    }
}
