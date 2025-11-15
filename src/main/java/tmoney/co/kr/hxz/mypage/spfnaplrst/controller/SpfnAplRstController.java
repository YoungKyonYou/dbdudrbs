package tmoney.co.kr.hxz.mypage.spfnaplrst.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.mypage.spfnaplrst.service.SpfnAplRstService;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstRspVO;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class SpfnAplRstController {
    private final SpfnAplRstService spfnAplRstService;

    @GetMapping("/spfnAplRst.do")
    public String readSpfnAplRst(
            @ModelAttribute @Valid SpfnAplRstReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        List<SpfnAplRstRspVO> result = spfnAplRstService.readSpfnAplRst(req, mbrsId);

        model.addAttribute("result", result);
        return "/hxz/mypage/spfnaplrst/spfnAplRst";
    }









}
