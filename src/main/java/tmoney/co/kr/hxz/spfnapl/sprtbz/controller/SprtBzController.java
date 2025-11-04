package tmoney.co.kr.hxz.spfnapl.sprtbz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.spfnapl.sprtbz.service.SprtBzService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/spfnapl")
public class SprtBzController {
    private SprtBzService sprtBzService;

    /**
     * 지원 사업 가입하기 화면
     *
     *
     * [process]
     * 1. 아이디를 param로 지원 사업 가입하기 화면 호출
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/sprtBzJoin.do")
    public String sprtBzJoin(
            Model model
    ) {
        model.addAttribute("mbrsId", "하하");
        return "/hxz/spfnapl/sprtbzjoin/step1";
    }

    /**
     * 지원 사업 가입하기 화면
     *
     *
     * [process]
     * 1. 아이디를 param로 지원 사업 가입하기 화면 호출
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/sprtBzJoin2.do")
    public String sprtBzJoin2(
            @ModelAttribute
            Model model
    ) {
        model.addAttribute("mbrsId", "하하");
        return "/hxz/spfnapl/sprtbzjoin/step2";
    }

    /**
     * 지원 사업 가입하기 화면
     *
     *
     * [process]
     * 1. 아이디를 param로 지원 사업 가입하기 화면 호출
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/sprtBzJoin3.do")
    public String sprtBzJoin3(
            Model model
    ) {
        model.addAttribute("mbrsId", "하하");
        return "/hxz/spfnapl/sprtbzjoin/step3";
    }
}
