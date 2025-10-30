package tmoney.co.kr.hxz.etc.sitemap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc")
public class SiteMapController {
    @GetMapping("/siteMap.do")
    public String siteMap() {
        return "/hxz/etc/sitemap/siteMap";
    }
}
