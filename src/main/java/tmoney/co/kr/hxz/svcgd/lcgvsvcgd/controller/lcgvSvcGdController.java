package tmoney.co.kr.hxz.svcgd.lcgvsvcgd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/svcgd")
public class lcgvSvcGdController {
    @GetMapping("/lcgvSvcGd.do")
    public String icgvSvcGd(Model model) {
        return "/hxz/svcgd/lcgvsvcgd/lcgvSvcGd";
    }
}
