package tmoney.co.kr.hxz.news.faq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.faq.service.FaqService;
import tmoney.co.kr.hxz.news.faq.vo.FaqRspVO;
import tmoney.co.kr.hxz.news.faq.vo.FaqSrchReqVO;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news/faq")
public class FaqController {
    private final FaqService faqService;

    /**
     * 자주하는 질문 조회
     * tbhxzm113 HXZ_자주하는질문
     *
     * [process]
     * 1. 검색 조건(제목, 내용)으로 HXZ_자주하는질문 테이블 내 자주하는질문 내역 호출
     * 2. 호출된 내역을 페이징으로 감싸서 pageData로 반환
     *
     * @param req
     * @param model
     * @return
     */
    @GetMapping(value = "/faq.do")
    public String readFaqPaging(
            @ModelAttribute FaqSrchReqVO req,
            Model model
    ) {
        PageDataVO<FaqRspVO> contents = faqService.readFaqPaging(req);


        model.addAttribute("pageData", contents);
        model.addAttribute("req" , req);
        return "/hxz/news/faq/faq";
    }

    /**
     * 자주하는 질문 상세 조회
     * tbhxzm113 HXZ_자주하는질문
     *
     * [process]
     * 1. 공지사항 번호(bltnNo)를 통해서 자주하는 질문 상세 조회
     * 2. 호출된 자주하는 질문 상세 정보를 faq로 반환
     *
     * @param bltnNo
     * @param model
     * @return
     */
    @GetMapping(value = "/faqDtl/{bltnNo}")
    public String readFaqDtl(
            @PathVariable("bltnNo") String bltnNo,
            Model model
    ) {
        FaqRspVO faq = faqService.readFaqDtl(bltnNo);
        faqService.updateFaqInqrNcnt(bltnNo);
        model.addAttribute("faq", faq);
        model.addAttribute("bltnNo" , bltnNo);
        return "/hxz/news/faq/faqDtl";
    }
}
