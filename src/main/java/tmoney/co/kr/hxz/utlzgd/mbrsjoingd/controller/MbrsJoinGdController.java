package tmoney.co.kr.hxz.utlzgd.mbrsjoingd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/utlzgd")
public class MbrsJoinGdController {
    @GetMapping("/mbrsJoinGd.do")
    public String mbrsjoinGd(Model model) {
        return "/hxz/utlzgd/mbrsjoingd/mbrsJoinGd";
    }
}
