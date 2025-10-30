package tmoney.co.kr.hxz.svcgd.lcgvsvcgd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/svcgd/lcgvsvcgd")
public class lcgvSvcGdController {
    @GetMapping
    public String icgvsvcgd(Model model) {
        return "/hxz/main/svcgd/lcgvsvcgd/lcgvsvcgd";
    }
}
