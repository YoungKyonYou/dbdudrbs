package tmoney.co.kr.hxz.etc.mbrsjoin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.etc.mbrsjoin.service.MbrsJoinService;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.CheckIdReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/etc/mbrsjoin")
public class MbrsJoinController {
    private final MbrsJoinService mbrsJoinService;

    /**
     * 회원 가입 화면 조회 step1
     *
     * [process]
     * 1. 회원 가입 화면 step1 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step1.do")
    public String readMbrsJoinStep1() {
        return "/hxz/etc/mbrsjoin/step1";
    }

    /**
     * 회원 가입 화면 조회 step2
     *
     * [process]
     * 1. 회원 가입 화면 step2 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step2.do")
    public String readMbrsJoinStep2() {
        return "/hxz/etc/mbrsjoin/step2";
    }

    /**
     * 회원 가입 화면 조회 step3
     *
     * [process]
     * 1. 회원 가입 화면 step3 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step3.do")
    public String readMbrsJoinStep3() {
        return "/hxz/etc/mbrsjoin/step3";
    }

    /**
     * 회원 정보 추가
     *
     * tbhxzm101 HXZ_회원정보
     * tbhxzh101 HXZ_회원정보변경이력
     *
     * [process]
     * 1. HXZ_회원정보 테이블 내의 회원 정보 추가
     * 2. HXZ_회원정보변경이력 테이블 회원정보 변경 이력 추가
     *
     * @param req
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> insertMbrsJoin(
            @Valid @RequestBody MbrsJoinInstReqVO req
    ) {
        mbrsJoinService.insertMbrsJoin(req);
        return ResponseEntity.ok().build();
    }

    /**
     * 아이디 중복 확인
     *
     * tbhxzm101 HXZ_회원정보
     *
     *
     * [process]
     * 1. HXZ_회원정보 테이블 내의 회원 정보 추가
     * 2. HXZ_회원정보변경이력 테이블 회원정보 변경 이력 추가
     *
     * @param req
     * @return
     */
    @PostMapping("/checkId")
    @ResponseBody
    public ResponseEntity<?> readMbrsCountById(
            @Valid @RequestBody CheckIdReqVO req
    ) {
        boolean isCheckId = mbrsJoinService.readMbrsCountById(req.getMbrsId());
        if (isCheckId) {
            return ResponseEntity.ok("아이디가 이미 존재합니다.");
        }
        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }


    /**
     * 회원 가입 화면 조회 step4
     *
     * [process]
     * 1. 회원 가입 화면 step4 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step4.do")
    public String readMbrsJoinStep4() {
        return "/hxz/etc/mbrsjoin/step4";
    }
}
