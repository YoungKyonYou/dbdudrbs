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
    @GetMapping("/bzaflt")
    public String bzaflt(Model model) {
        return "/hxz/main/pltfintd/bzaflt";
    }

    @GetMapping("/mdlby")
    public String mdlby(Model model) {
        return "/hxz/main/pltfintd/mdlby";
    }

    @GetMapping("/pltfintd")
    public String pltfintd(Model model) {
        return "/hxz/main/pltfintd/pltfintd";
    }
}
