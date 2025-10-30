package tmoney.co.kr.hxz.etc.utlzstpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc")
public class UtlzStplController {
    @GetMapping("/utlzStpl.do")
    public String siteMap() {
        return "/hxz/etc/utlzstpl/utlzStpl";
    }
}
