package tmoney.co.kr.hxz.pltfintd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pltfintd")
public class PltfIntdController {
    @GetMapping("/bzAflt.do")
    public String bzAflt(Model model) {
        return "/hxz/pltfintd/bzAflt";
    }

    @GetMapping("/mdlBy.do")
    public String mdlBy(Model model) {
        return "/hxz/pltfintd/mdlBy";
    }

    @GetMapping("/pltfIntd.do")
    public String pltfIntd(Model model) {
        return "/hxz/pltfintd/pltfIntd";
    }
}
