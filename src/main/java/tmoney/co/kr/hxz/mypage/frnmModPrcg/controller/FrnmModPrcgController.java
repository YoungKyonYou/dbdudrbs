package tmoney.co.kr.hxz.mypage.frnmModPrcg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.mypage.frnmModPrcg.service.FrnmModPrcgService;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvReqVO;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class FrnmModPrcgController {
    private final FrnmModPrcgService frnmModPrcgService;
    /**
     * 개명처리 화면
     *
     * [process]
     * 1. 개명처리 화면 이동
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/frnmModPrcg.do")
    public String frnmModPrcg(
            Model model
    ) {
        String mbrsId = "tmoney002";
        String mbrsNm = "홍길동";

        model.addAttribute("mbrsNm", mbrsNm);
        return "/hxz/mypage/frnmmodprcg/frnmModPrcg";
    }

    /**
     * 개명처리
     * tbhxzm102 HXZ_회원서비스정보
     *
     * [process]
     * 1. 계좌 인증 정보를 통해 회원서비스정보의 예금주명 update
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/frnmModPrcg.do")
    @ResponseBody
    public ResponseEntity<?> frnmModPrcg(
            @ModelAttribute @Valid MyLcgvReqVO req
    ) {
        String mbrsId = "tmoney002";

        return ResponseEntity.ok().build();
    }
}
