package tmoney.co.kr.hxz.news.ntcmttr.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.ntcmttr.service.NtcMttrService;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrRspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrSrchReqVO;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news/ntcmttr")
public class NtcMttrController {
    private final NtcMttrService ntcMttrService;

    /**
     * 공지 사항 조회
     * tbhxzm113 HXZ_공지사항관리
     *
     * [process]
     * 1. 검색 조건(제목, 내용)으로 HXZ_공지사항관리 테이블 내 공지사항 내역 호출
     * 2. 호출된 내역을 페이징으로 감싸서 pageData로 반환
     *
     * @param req
     * @param model
     * @return
     */
    @GetMapping(value = "/ntcMttr.do")
    public String readNtcMttrPaging(
            @ModelAttribute NtcMttrSrchReqVO req,
            @ParameterObject Model model
    ) {
        PageDataVO<NtcMttrRspVO> contents = ntcMttrService.readNtcMttrPaging(req);

        model.addAttribute("pageData", contents);
        model.addAttribute("req", req);
        return "/hxz/news/ntcmttr/ntcMttr";
    }



    /**
     * 공지 사항 상세 조회
     * tbhxzm113 HXZ_공지사항관리
     *
     * [process]
     * 1. 공지사항 번호(bltnNo)를 통해서 공지 사항 상세 내역 조회
     * 2. 호출된 공지 사항 상세 정보를 ntcMttr로 반환
     *
     * @param bltnNo
     * @param model
     * @return
     */
    @GetMapping(value = "/ntcMttrDtl/{bltnNo}")
    public String readNtcMttrDtl(
            @PathVariable("bltnNo") String bltnNo,
            Model model
    ) {
        NtcMttrRspVO ntcMttr = ntcMttrService.readNtcMttrDtl(bltnNo);

        model.addAttribute("ntcMttr", ntcMttr);
        model.addAttribute("bltnNo" , bltnNo);
        return "/hxz/news/ntcmttr/ntcMttrDtl";

    }
}
