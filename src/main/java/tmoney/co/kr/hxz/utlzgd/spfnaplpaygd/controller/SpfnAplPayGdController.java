package tmoney.co.kr.hxz.utlzgd.spfnaplpaygd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/utlzgd")
public class SpfnAplPayGdController {
    @GetMapping("/spfnAplPayGd.do")
    public String spfnAplPayGd(Model model) {
        return "/hxz/utlzgd/spfnaplpaygd/spfnAplPayGd";
    }
}
