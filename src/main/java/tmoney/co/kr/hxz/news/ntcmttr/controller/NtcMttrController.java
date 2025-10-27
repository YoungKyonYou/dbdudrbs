package tmoney.co.kr.hxz.news.ntcmttr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.ntcmttr.service.NtcMttrService;
import tmoney.co.kr.hxz.news.ntcmttr.vo.RspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.SrchReqVO;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news/ntcmttr")
public class NtcMttrController {
    private final NtcMttrService ntcMttrService;

    @GetMapping(value = "/ntcMttr.do")
    public String readNtcMttrPaging(
            @ModelAttribute SrchReqVO req,
            Model model
    ) {
        PageDataVO<RspVO> contents = ntcMttrService.readNtcMttrPaging(req);

        model.addAttribute("contents", contents);
        return "/hxz/news/ntcmttr/ntcMttr";
    }
}
