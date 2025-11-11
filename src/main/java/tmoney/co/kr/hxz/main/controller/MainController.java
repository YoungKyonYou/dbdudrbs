package tmoney.co.kr.hxz.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tmoney.co.kr.hxz.main.service.MainService;
import tmoney.co.kr.hxz.main.vo.MainNtcRspVO;
import tmoney.co.kr.hxz.main.vo.MainSvcRspVO;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {
    private final MainService mainService;
    /**
     * 메인 조회
     * tbhxzm113 HXZ_공지사항관리
     *
     * [process]
     * 1. HXZ_공지사항관리 테이블 내 공지사항 내역 최근 5개 호출
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        String mbrsId = "tmoney001";
        List<MainNtcRspVO> mainNtc = mainService.readMainNtcList();
        List<MainSvcRspVO> mainSvc = mainService.readMainSvcList(mbrsId);
        model.addAttribute("mainNtc", mainNtc);
        model.addAttribute("mainSvc", mainSvc);
        return "/hxz/main/index";
    }

    /**
     * 가입한 전체 서비스 조회
     *
     * [process]
     * 1. 가입 가능한 전체 서비스 조회
     *
     * @param
     * @param model
     * @return
     */
    @PostMapping("/svcList")
    @ResponseBody
    public String readSvcList(Model model) {
        List<MainSvcRspVO> mainSvcList = mainService.readMainSvcList();

        model.addAttribute("mainSvcList", mainSvcList);
        return "/hxz/main/index";
    }
}
