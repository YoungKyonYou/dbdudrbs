package tmoney.co.kr.hxz.etc.indlinfstpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc")
public class IndlInfStplController {
    @GetMapping("/indlInfStpl.do")
    public String indlInfStpl() {
        return "/hxz/etc/indlinfstpl/indlInfStpl";
    }
}
