package tmoney.co.kr.hxz.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.main.service.MainService;
import tmoney.co.kr.hxz.main.vo.*;
import tmoney.co.kr.hxz.main.vo.lcgv.LcgvMainReqVO;
import tmoney.co.kr.hxz.main.vo.lcgv.LcgvMainRspVO;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {
    private final MainService mainService;
    /**
     * 메인 조회
     * tbhxzm009 HXZ_기관연계정보
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzm113 HXZ_공지사항관리
     *
     * [process]
     * 1. HXZ_공지사항관리 테이블 내 공지사항 내역 최근 5개 호출
     * 2. 현재 가입한 서비스 내역 조회
     * 3. 가입한 서비스를 제외한 전체 서비스 내역 조회
     * 4. 지자체 서비스 내역 조회(OrgInfList)
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/main.do")
    public String index(Model model) {
        String mbrsId = "tmoney002";

        List<MySvcRspVO> mySvcList = mainService.readMySvcList(mbrsId);
        List<MainSvcRspVO> mainSvcList = mainService.readMainSvcList(mbrsId);
        List<OrgInfRspVO> orgInfList = mainService.readOrgInfList(mbrsId);

        List<MainNtcRspVO> mainNtcList = mainService.readMainNtcList();


        model.addAttribute("mySvcList", mySvcList);
        model.addAttribute("mainSvcList", mainSvcList);
        model.addAttribute("orgInfList", orgInfList);

        model.addAttribute("mainNtcList", mainNtcList);
        return "/hxz/main/index";
    }

    /**
     * 지자체 메인 조회
     * tbhxzm009 HXZ_기관연계정보
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzd214 HXZ_지원금지급내역
     * tbhxzm113 HXZ_공지사항관리
     *
     * [process]
     * 1. HXZ_공지사항관리 테이블 내 공지사항 내역 최근 5개 호출
     * 2. 현재 가입한 서비스 내역 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/lcgvMain.do")
    public String lcgvMain(
            @ModelAttribute LcgvMainReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney002";

        LcgvMainRspVO result = mainService.readLcgvMain(mbrsId);

        model.addAttribute("result", result);
        return "/hxz/main/index";
    }
}
