package tmoney.co.kr.hxz.utlzgd.mbrsjoingd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/utlzgd/mbrsjoingd")
public class MbrsJoinGdController {
    @GetMapping
    public String mbrsjoingd(Model model) {
        return "/hxz/main/utlzgd/mbrsjoingd/mbrsjoingd";
    }
}
