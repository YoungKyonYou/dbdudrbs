package tmoney.co.kr.hxz.mypage.mypage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.mypage.mypage.service.MyPageService;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvReqVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyPageRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.apl.MyAplRspVO;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    /**
     * 지자체 마이페이지 대시보드 조회
     * tbhxzm009 HXZ_기관연계정보
     * tbhxzm101 HXZ_회원정보
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzc002 HXZ_상세코드
     *
     * tbhxzm203 HXZ_지원금신청
     *
     * [process]
     * 1. 회원서비스정보에서 현재 서비스의 정보(카드번호,계좌,예금주명) 조회
     * 2. 상세코드에서 회원서비스정보에 해당하는 은행코드에 따른 은행명 조회
     * 3. 기관연계정보에서 기관 이름 조회(지자체 명)
     * 4. 지원금신청 정보에서 회원서비스 정보에 해당하는 지원금 신청 정보(신청상태, 신청일시) 조회
     *
     * @param
     * @param model
     * @return id, 지원금 신청결과, 날짜, 카드정보, 지자체 기관정보, 계좌정보
     */
    @GetMapping("/dashboard.do")
    public String dashboard(
            @ModelAttribute @Valid MyLcgvReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";

        MyPageRspVO result = myPageService.readMyPage(req, mbrsId);
        MyAplRspVO myApl = myPageService.readMyApl(req, mbrsId);
        model.addAttribute("result", result);
        model.addAttribute("myApl", myApl);
        return "/hxz/mypage/dashboard";
    }



    /**
     * 내 지자체 정보 조회
     * tbhxzm009 HXZ_기관연계정보
     * tbhxzm102 HXZ_회원서비스정보
     *
     *
     * [process]
     * 1. 해당 서비스의 가장 최신 거주지 인증이력 조회
     * 2. 현재 가입한 서비스 내역 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/myLcgv.do")
    public String myLcgv(
            @ModelAttribute @Valid MyLcgvReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";

        List<MyLcgvRspVO> result = myPageService.readMyLcgv(req, mbrsId);
        String tpwOrgNm = myPageService.readMyOrgNm(req, mbrsId);
        model.addAttribute("result", result);
        model.addAttribute("tpwOrgNm", tpwOrgNm);
        return "/hxz/mypage/mylcgv/myLcgv";
    }
}
