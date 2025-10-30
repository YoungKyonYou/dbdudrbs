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
    public String bzaflt(Model model) {
        return "/hxz/main/pltfintd/bzAflt";
    }

    @GetMapping("/mdlBy.do")
    public String mdlby(Model model) {
        return "/hxz/main/pltfintd/mdlBy";
    }

    @GetMapping("/pltfIntd.do")
    public String pltfintd(Model model) {
        return "/hxz/main/pltfintd/pltfIntd";
    }
}
