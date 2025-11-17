package tmoney.co.kr.hxz.lcgvmain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import tmoney.co.kr.hxz.lcgvmain.service.LcgvMainService;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainReqVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainRspVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvNtcRspVO;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class LcgvMainController {
    private final LcgvMainService lcgvMainService;
    /**
     * 지자체 메인 조회
     * tbhxzm009 HXZ_기관연계정보
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzd214 HXZ_지원금지급내역
     * tbhxzm113 HXZ_공지사항관리
     * tbhxzm116 HXZ_배너관리
     *
     * [process]
     * 1. HXZ_공지사항관리 테이블 내 현재 서비스에 해당하는 공지사항 내역 최근 5개 호출
     * 2. 회원이 가입한 교통복지서비스의 가장 최근 지원금지급내역 호출
     * 3. 현재 교통복지서비스의 배너 첨부파일 url 호출
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/lcgvMain.do")
    public String lcgvMain(
            @ModelAttribute @Valid LcgvMainReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";

        LcgvMainRspVO result = lcgvMainService.readLcgvMain(req, mbrsId);
        List<LcgvNtcRspVO> lcgvNtcList = lcgvMainService.readLcgvNtcList(req.getTpwSvcId());

        model.addAttribute("lcgvNtcList", lcgvNtcList);
        model.addAttribute("result", result);
        return result.getUrl();
    }

    @GetMapping("/lcgvMain1.do")
    public String lcgvMain1(
            @ModelAttribute LcgvMainReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";

        LcgvMainRspVO result = lcgvMainService.readLcgvMain(req, mbrsId);
        List<LcgvNtcRspVO> lcgvNtcList = lcgvMainService.readLcgvNtcList(req.getTpwSvcId());

        model.addAttribute("lcgvNtcList", lcgvNtcList);
        model.addAttribute("result", result);
        return "/hxz/main/lcgvMain";
    }
}
