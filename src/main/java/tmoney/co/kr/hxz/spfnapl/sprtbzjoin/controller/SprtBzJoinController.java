package tmoney.co.kr.hxz.spfnapl.sprtbz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.spfnapl.sprtbz.service.SprtBzJoinService;
import tmoney.co.kr.hxz.spfnapl.sprtbz.vo.SprtBzReqVO;

@RequiredArgsConstructor
@Controller
@RequestMapping("/spfnapl")
public class SprtBzJoinController {
    private SprtBzJoinService sprtBzJoinService;

    /**
     * 지원 사업 가입 + 거주지 인증 화면
     *
     *
     * [process]
     * 1. 아이디를 param로 지원 사업 가입하기 화면 호출
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/rsdcAuth.do")
    public String rscdAuth(
            Model model
    ) {
        model.addAttribute("mbrsId", "tmoney001");
        return "/hxz/spfnapl/sprtbzjoin/step1";
    }

    /**
     * 서비스 인증 및 유형 선택 화면
     *
     *
     * [process]
     * 1. 서비스 인증 및 유형 선택 화면 호출
     * 2. 서비스ID에 따라 서비스 유형 조회
     * 3. 해당 서비스의 서비스유형별 회원유형코드 조회
     * 4. 회원유형코드로 회원분류코드 조회(일반인지 판별)
     * 4. 기관코드로 기관별 회원유형코드의 유형적용최소값, 유형적용최대값 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/sprtBz.do")
    public String readSprtBz(
            Model model
    ) {
        String mbrsId = "tmoney001";
        String tpwSvcId = "SVC002";
        SprtBzReqVO req =  new SprtBzReqVO();
        sprtBzJoinService.
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
