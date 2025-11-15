package tmoney.co.kr.hxz.mypage.spnfptinqr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.spnfptinqr.service.SpnfPtInqrService;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrRspVO;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class SpnfPtInqrController {
    private final SpnfPtInqrService spnfPtInqrService;

    @GetMapping("/spnfPtInqr.do")
    public String readSpnfPtInqrPaging(
            @ModelAttribute @Valid SpnfPtInqrReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        PageData<SpnfPtInqrRspVO> contents = spnfPtInqrService.readSpnfPtInqrPaging(req, mbrsId);

        model.addAttribute("pageData", contents);
        return "/hxz/mypage/spnfPtInqr";
    }
}
