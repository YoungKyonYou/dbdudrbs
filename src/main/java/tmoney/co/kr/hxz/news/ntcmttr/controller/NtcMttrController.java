package tmoney.co.kr.hxz.news.ntcmttr.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
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
     * 1. 검색 조건(제목, 내용)으로 HXZ_공지사항관리 테이블 내 사용여부 Y인 공지사항 내역 조회
     * 2. 교통복지게시일시 순으로 최신순, 메인노출여부가 Y 순으로 호출
     * 3. 호출된 내역을 페이징으로 감싸서 pageData로 반환
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
        ntcMttrService.updateNtcInqrNcnt(bltnNo);
        model.addAttribute("ntcMttr", ntcMttr);
        model.addAttribute("bltnNo" , bltnNo);
        return "/hxz/news/ntcmttr/ntcMttrDtl";

    }

    /**
     * 이전 공지사항 조회
     * tbhxzm113 HXZ_공지사항관리
     *
     * [process]
     * 1. 현재 공지사항의 게시일 (이전,다음) 공지사항ID 반환
     *
     * @param bltnNo
     * @return
     */
    @GetMapping(value = "/prev/{bltnNo}")
    public ResponseEntity<?> readPrevNtcMttr(
            @PathVariable("bltnNo") String bltnNo
    ) {
        NtcMttrRspVO ntcMttr = ntcMttrService.readPrevNtcMttr(bltnNo);
        if(ntcMttr == null) return ResponseEntity.ok().body("이전 공지사항이 없습니다.");

        return ResponseEntity.ok().body(ntcMttr.getBltnNo());
    }


    /**
     * 다음 공지사항 조회
     * tbhxzm113 HXZ_공지사항관리
     *
     * [process]
     * 1. 현재 공지사항의 게시일 다음 공지사항ID 반환
     *
     * @param bltnNo
     * @return
     */
    @GetMapping(value = "/next/{bltnNo}")
    public ResponseEntity<?> readNextNtcMttr(
            @PathVariable("bltnNo") String bltnNo
    ) {
        NtcMttrRspVO ntcMttr = ntcMttrService.readNextNtcMttr(bltnNo);
        if(ntcMttr == null) return ResponseEntity.ok().body("다음 공지사항이 없습니다.");

        return ResponseEntity.ok().body(ntcMttr.getBltnNo());
    }
}
