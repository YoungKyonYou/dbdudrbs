package tmoney.co.kr.hxz.spfnapl.sprtbzjoin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.service.SprtBzJoinService;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzReqVO;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzRspVO;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/spfnapl")
public class SprtBzJoinController {
    private final SprtBzJoinService sprtBzJoinService;

    /**
     * 지원 사업 가입 + 거주지 인증 화면
     * tbhxzh103 거주지인증이력
     * tbhxzm102 회원서비스
     *
     * [process]
     * 1. 거주지 인증 후, 거주지 인증 이력 저장
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/rsdcAuth.do")
    public String rsdcAuth(
            Model model
    ) {
        model.addAttribute("mbrsId", "tmoney001");
        return "/hxz/spfnapl/sprtbzjoin/rsdcAuth";
    }

    /**
     * 서비스 인증 및 유형 선택 화면
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
     * 4. 기관코드로 기관별 회원유형코드의 유형적용최소값, 유형적용최대값 조회
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/sprtBz.do")
    public String readSprtBz(
            Model model
    ) {
        String mbrsId = "tmoney001";
        String tpwSvcId = "SVC010";
        String orgCd = "ORG0002";
        SprtBzReqVO req =  new SprtBzReqVO(mbrsId, tpwSvcId, orgCd);
        List<SprtBzRspVO> result = sprtBzJoinService.readSprtBz(req);
        model.addAttribute("mbrsId", mbrsId);
        model.addAttribute("result", result);
        return "/hxz/spfnapl/sprtbzjoin/sprtBz";
    }

    /**
     * 회원 서비스 가입
     * tbhxzm102 HXZ_회원서비스정보
     * tbhxzm115 HXZ_첨부파일정보
     *
     * [process]
     * 1. 회원 서비스 정보 등록
     * 2. 첨부파일 등록
     *
     *
     * @param
     * @param model
     * @return
     */
    @PostMapping(value = "/sprtBz")
    public String sprtBzJoin(
            Model model
    ) {
        String mbrsId = "tmoney001";
        String tpwSvcId = "SVC010";
        String orgCd = "ORG0002";
        SprtBzReqVO req =  new SprtBzReqVO(mbrsId, tpwSvcId, orgCd);
        List<SprtBzRspVO> result = sprtBzJoinService.readSprtBz(req);
        model.addAttribute("mbrsId", mbrsId);
        model.addAttribute("result", result);
        return "/hxz/spfnapl/sprtbzjoin/sprtBz";
    }

    /**
     * 지원 사업 가입 완료 화면
     *
     *
     * [process]
     * 1. 지원 사업 가입 완료 화면
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping(value = "/sprtBzJoinFn.do")
    public String sprtBzJoinFn(
            Model model
    ) {
        return "/hxz/spfnapl/sprtbzjoin/sprtBzJoinFn";
    }
}
