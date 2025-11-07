package tmoney.co.kr.hxz.svcjoin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tmoney.co.kr.hxz.svcjoin.service.SvcJoinService;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svccncn.SvcCncnReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinInstReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfRspVO;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/svcjoin")
public class SvcJoinController {
    private final SvcJoinService svcJoinService;

    /**
     * 지원 사업(서비스) 가입 + 거주지 인증 화면
     * tbhxzm009 HXZ_기관연계정보
     *
     * [process]
     * 1. 기관코드로 기관연계정보 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/rsdcAuth.do")
    public String readRsdcAuth(
            @ModelAttribute OrgInfReqVO req,
            Model model
    ) {
        OrgInfRspVO result = svcJoinService.readOrgInf(req);
        model.addAttribute("result", result);
        return "/hxz/svcjoin/rsdcAuth";
    }


    /**
     * 거주지 인증
     * tbhxzm008 HXZ_행정동코드관리
     * tbhxzh103 HXZ_거주지인증이력
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzm202 HXZ_교통복지서비스유형관리
     *
     *
     * [process]
     * 1. 주민등록번호 입력
     * 2. 주민등록번호와 기관코드로 거주지 인증 후, 거주지 인증 이력 저장
     * 3. 거주지 인증 후, 행정동코드로 기관코드 조회(신청한 서비스의 기관코드와 다르면 서비스 가입 불가 모달)
     * 4. 현재 가입하려는 서비스 정보 조회
     * 5. 이전 가입한 서비스 내역 조회
     * 6. 이전 내역의 기관코드와 다를 경우 해지하시겠습니까 모달 요청
     * 6-1. 이전 내역의 기관코드가 같지만, 현재 서비스의 지원중복여부가 N일경우
     * 6-2. 이전 내역의 기관코드가 같지만, 이전 내역이 지원중복여부가 N일경우
     * 7. 이전 내역이 없을 경우 서비스 유형 선택 화면 이동
     * 7-1. 기관코드가 같은데 현재 서비스유형과 이전 서비스유형이 지원중복여부가 Y일 경우
     *
     * @param req
     * @param model
     * @return
     */
    @PostMapping(value = "/rsdcAuth")
    public ResponseEntity<?> rsdcAuth(
            @RequestBody RsdcAuthReqVO req,
            Model model
    ) {
        String mbrsId = "tmoney001";
        String result = svcJoinService.rsdcAuth(req, mbrsId);
        model.addAttribute("mbrsId", mbrsId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * 회원 서비스 해지
     * tbhxzh103 HXZ_거주지인증이력
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzh102 HXZ_회원서비스변경이력
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzm202 HXZ_교통복지서비스유형관리
     *
     *
     * [process]
     * 1. 해지 시, 이전 서비스 내역 조회
     * 2. 이전 회원서비스 정보를 전출일자 D-1, 교통복지회원서비스상태코드 해지, 회원서비스해지일자 D-Day 로 update
     * 3. 이전 거주지 인증이력 전출일자 D-1로 update
     * 4. 이전 회원서비스의 카드변경이력 카드종료일자 전출일자 D-1로 update
     * 5. 회원서비스정보변경이력 insert
     *
     * @param
     * @param model
     * @return
     */
    @PostMapping(value = "/svcCncn")
    public ResponseEntity<?> svcCncn(
            @RequestPart SvcCncnReqVO req,
            @RequestPart(value = "file", required = false) MultipartFile file,
            Model model
    ) {
        String mbrsId = "tmoney001";
        String tpwSvcId = "SVC010";

        svcJoinService.svcCncn(req, mbrsId);
        model.addAttribute("mbrsId", mbrsId);
        return ResponseEntity.ok().body(tpwSvcId);
    }

    /**
     * 서비스 인증 및 서비스 유형 선택 화면
     * 서비스 ID별 서비스 유형 및 회원유형/기관별 범위 조회
     * tbhxzm103 HXZ_회원유형분류관리
     * tbhxzm104 HXZ_기관회원유형관리
     * tbhxzm201 HXZ_교통복지서비스관리
     * tbhxzm202 HXZ_교통복지서비스유형관리
     *
     * [process]
     * 1. 서비스 인증 및 유형 선택 화면 호출
     * 2. 서비스ID에 따라 서비스 유형 조회
     * 3. 해당 서비스의 서비스유형별 회원유형코드 조회
     * 4. 회원유형코드로 회원분류코드 조회(일반인지 판별)
     * 5. 기관코드로 기관별 회원유형코드의 유형적용최소값, 유형적용최대값 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/svcJoin.do")
    public String readSvcJoin(
            @ModelAttribute SvcJoinReqVO req,
            Model model
    ) {
        List<SvcJoinRspVO> result = svcJoinService.readSvcJoin(req);
        model.addAttribute("result", result);
        // return "/hxz/sprtbzjoin/sprtBz";
        return "/hxz/svcJoin/sprtBz";
    }

    /**
     * 서비스 가입
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm115 HXZ_첨부파일정보
     *
     * [process]
     * 1. 회원 서비스 정보 등록
     * 2. 첨부파일 등록
     *
     * @param
     * @param model
     * @return
     */
    @PostMapping(value = "/svcJoin")
    public String insertSvcJoin(
            @RequestPart SvcJoinInstReqVO req,
            @RequestPart(value = "file", required = false) MultipartFile file,
            Model model
    ) {
        String mbrsId = "tmoney001";
        svcJoinService.svcJoin(req, mbrsId);
        model.addAttribute("mbrsId", mbrsId);
        
        //return "/hxz/sprtbzjoin/sprtBz";
         return "/hxz/svcJoinFn/sprtBz";
    }

    /**
     * 서비스 가입 완료 화면
     *
     *
     * [process]
     * 1. 서비스 가입 완료 화면
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/svcJoinFn.do")
    public String svcJoinFn(
            Model model
    ) {
        return "/hxz/svcjoin/svcJoinFn";
    }
}
