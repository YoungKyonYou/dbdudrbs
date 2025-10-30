package tmoney.co.kr.hxz.utlzgd.spfnaplpaygd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/utlzgd/spfnaplpaygd")
public class SpfnAplPayGdController {
    @GetMapping
    public String mbrsjoingd(Model model) {
        return "/hxz/main/utlzgd/spfnaplpaygd/spfnaplpaygd";
    }
}
