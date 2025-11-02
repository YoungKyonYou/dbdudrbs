package tmoney.co.kr.hxz.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.main.service.MainService;
import tmoney.co.kr.hxz.main.vo.MainNtcRspVO;

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
        List<MainNtcRspVO> mainNtc = mainService.readMainNtcList();

        model.addAttribute("mainNtc", mainNtc);
        return "/hxz/main/index";
    }
}
