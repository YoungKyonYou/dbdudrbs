package tmoney.co.kr.hxz.mypage.spfnptinqr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.spfnptinqr.service.SpfnPtInqrService;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrRspVO;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class SpfnPtInqrController {
    private final SpfnPtInqrService spfnPtInqrService;

    /**
     * 지원금 지급 내역 조회
     *
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzm214 HXZ_지원금지급내역
     * tbhxzc002 HXZ_상세코드관리
     *
     * [process]
     * 1. 현재 가입한 서비스에서 신청한 지원금 지급 내역 조회
     * 2. 상세코드관리에서 은행코드('0041')에 해당하는 상세코드명(은행명) 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/spfnPtInqr.do")
    public String readSpfnPtInqrPaging(
            @ModelAttribute @Valid SpfnPtInqrReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";
        PageData<SpfnPtInqrRspVO> contents = spfnPtInqrService.readSpfnPtInqrPaging(req, mbrsId);

        model.addAttribute("pageData", contents);
        model.addAttribute("req", req);
        return "/hxz/mypage/spfnptinqr/spfnPtInqr";
    }
}
