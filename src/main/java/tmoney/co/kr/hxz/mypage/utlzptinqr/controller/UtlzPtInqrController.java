package tmoney.co.kr.hxz.mypage.utlzptinqr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.utlzptinqr.service.UtlzPtInqrService;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrRspVO;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class UtlzPtInqrController {
    private final UtlzPtInqrService utlzPtInqrService;

    /**
     * 이용 내역 조회
     *
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzm209 HXZ_카드별일집계
     *
     * [process]
     * 1. 현재 가입한 서비스에서 신청한 지원금 카드별일계 조회
     *
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/utlzPtInqr.do")
    public String readUtlzPtInqrPaging(
            @ModelAttribute @Valid UtlzPtInqrReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        PageData<UtlzPtInqrRspVO> contents = utlzPtInqrService.readUtlzPtInqrPaging(req, mbrsId);

        model.addAttribute("pageData", contents);
        model.addAttribute("req", req);
        return "/hxz/mypage/utlzptinqr/utlzPtInqr";
    }
}
