package tmoney.co.kr.hxz.etc.indlinfprcg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc")
public class IndlInfPrcgController {
    @GetMapping("/indlInfPrcg.do")
    public String indlInfPrcg() {
        return "/hxz/etc/indlinfprcg/indlInfPrcg";
    }
}
