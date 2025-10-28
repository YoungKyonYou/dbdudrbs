package tmoney.co.kr.hxz.news.ntcmttr.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.ntcmttr.service.NtcMttrService;
import tmoney.co.kr.hxz.news.ntcmttr.vo.RspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.SrchReqVO;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news/ntcmttr")
public class NtcMttrController {
    private final NtcMttrService ntcMttrService;

    @GetMapping(value = "/ntcMttr.do")
    public String readNtcMttrPaging(
            @ModelAttribute SrchReqVO req,
            @ParameterObject Model model
    ) {
        PageDataVO<RspVO> contents = ntcMttrService.readNtcMttrPaging(req);

        model.addAttribute("pageData", contents);
        model.addAttribute("req", req);
        return "/hxz/news/ntcmttr/ntcMttr";
    }

    @GetMapping(value = "/ntcMttr2.do")
    public String readNtcMttrPaging2(
            @ModelAttribute SrchReqVO req,
            @ParameterObject Model model
    ) {
        PageDataVO<RspVO> contents = ntcMttrService.readNtcMttrPaging(req);

        model.addAttribute("pageData", contents);
        model.addAttribute("req", req);
        return "/hxz/news/ntcmttr/ntcMttr_original";
    }
}
