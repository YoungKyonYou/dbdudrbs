package tmoney.co.kr.hxz.mypage.utlzptinqr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.spfnptinqr.service.SpfnPtInqrService;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrRspVO;
import tmoney.co.kr.hxz.mypage.utlzptinqr.service.UtlzPtInqrService;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrRspVO;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class UtlzPtInqrController {
    private final UtlzPtInqrService utlzPtInqrService;

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
