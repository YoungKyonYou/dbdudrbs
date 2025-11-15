package tmoney.co.kr.hxz.mypage.spfnptinqr.controller;

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

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class SpfnPtInqrController {
    private final SpfnPtInqrService spfnPtInqrService;

    @GetMapping("/spfnPtInqr.do")
    public String readSpfnPtInqrPaging(
            @ModelAttribute @Valid SpfnPtInqrReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        PageData<SpfnPtInqrRspVO> contents = spfnPtInqrService.readSpfnPtInqrPaging(req, mbrsId);

        model.addAttribute("pageData", contents);
        model.addAttribute("req", req);
        return "/hxz/mypage/spfnptinqr/spfnPtInqr";
    }
}
