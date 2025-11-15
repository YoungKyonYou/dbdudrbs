package tmoney.co.kr.hxz.mypage.spnfaplrst.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.mypage.spnfaplrst.service.SpnfAplRstService;
import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstRspVO;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class SpnfAplRstController {
    private final SpnfAplRstService spnfAplRstService;

    @GetMapping("/spnfAplRst.do")
    public String readSpnfAplRst(
            @ModelAttribute @Valid SpnfAplRstReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        List<SpnfAplRstRspVO> result = spnfAplRstService.readSpnfAplRst(req, mbrsId);

        model.addAttribute("result", result);
        return "/hxz/mypage/spnfPtInqr";
    }









}
