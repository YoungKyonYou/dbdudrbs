package tmoney.co.kr.hxz.mypage.spfnaplrst.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.mypage.spfnaplrst.service.SpfnAplRstService;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstRspVO;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class SpfnAplRstController {
    private final SpfnAplRstService spfnAplRstService;

    /**
     * 지원금 신청 결과 조회
     * tbhxzm009 HXZ_기관연계정보
     * tbhxzm101 HXZ_회원정보
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzm203 HXZ_지원금신청
     *
     * [process]
     * 1. 현재 가입한 서비스에서 신청한 지원금 신청 결과 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/spfnAplRst.do")
    public String readSpfnAplRst(
            @ModelAttribute @Valid SpfnAplRstReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        List<SpfnAplRstRspVO> result = spfnAplRstService.readSpfnAplRst(req, mbrsId);

        model.addAttribute("result", result);
        return "/hxz/mypage/spfnaplrst/spfnAplRst";
    }









}
