package tmoney.co.kr.hxz.etc.mbrsjoin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.etc.mbrsinf.service.MbrsInfService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc/mbrsjoin")
public class MbrsJoinController {
    private final MbrsInfService mbrsInfService;

    /**
     * 회원 가입 화면 조회 step1
     *
     * [process]
     * 1. 회원 가입 화면 step1 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step1.do")
    public String readMbrsJoinStep1() {
        return "/hxz/etc/mbrsjoin/step1";
    }

    /**
     * 회원 가입 화면 조회 step2
     *
     * [process]
     * 1. 회원 가입 화면 step2 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step2.do")
    public String readMbrsJoinStep2() {
        return "/hxz/etc/mbrsjoin/step2";
    }

    /**
     * 회원 가입 화면 조회 step3
     *
     * [process]
     * 1. 회원 가입 화면 step3 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step3.do")
    public String readMbrsJoinStep3() {
        return "/hxz/etc/mbrsjoin/step3";
    }
}
