package tmoney.co.kr.hxz.etc.mbrsjoin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tmoney.co.kr.hxz.common.onboard.vo.SignupVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.service.MbrsJoinService;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.CheckIdReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.rcpt.MbrsJoinFinalizeVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/etc/mbrsjoin")
@Validated
public class MbrsJoinController {
    private final MbrsJoinService mbrsJoinService;


    /**
     * 회원 가입 화면 조회 step1
     *
     * [process]
     * 1. 약관 동의 화면
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
     * 회원 가입 화면 조회 step2 start
     *
     * [process]
     * 1. [step2] 본인 인증 화면
     *
     * @return
     */
    @GetMapping("/start")
    public String start(HttpServletRequest req, HttpServletResponse res) {
        mbrsJoinService.start(req, res);
        return "redirect:/etc/mbrsjoin/step2.do";
    }

    /**
     * 회원 가입 화면 조회 step2
     *
     * [process]
     * 1. [step2] 본인 인증 화면
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step2.do")
    public String mbrsJoinStep2(HttpServletRequest req, Model model) {
        SignupVO signupVO = mbrsJoinService.mbrsJoinStep2(req, model);
        model.addAttribute("signup", signupVO);
        return "/hxz/etc/mbrsjoin/step2";
    }


    /**
     * [STEP 2 완료 → STEP 3 진입용] 본인인증 결과 수신
     */
    @PostMapping(value = "/step2/complete", produces = "application/json")
    @ResponseBody
    public Map<String, String> submitAuthResult(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            @RequestParam("authType") String authType,
            HttpServletRequest req, HttpServletResponse res) {

        Map<String, String> result = mbrsJoinService.submitAuthResult(token, nonce, req, res, authType);
        // 프론트로 응답 (step3 이동 시 사용)
        return result;
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
     * [STEP 3 완료 → STEP 4 진입용] 회원 정보 입력 결과 수신
     */
    @PostMapping(value = "/step3/complete", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Map<String, String> submitInfResult(
            @CookieValue("onb") String token,
            @RequestHeader("X-Nonce") String nonce,
            @RequestBody @Valid MbrsJoinInstReqVO payload,
            HttpServletRequest req, HttpServletResponse res) {

        Map<String, String> result = mbrsJoinService.submitInfResult(token, nonce, payload, req, res);
        // 프론트로 응답 (step3 이동 시 사용)
        return result;
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
     * @param body
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> insertMbrsJoin(
            @CookieValue("onb_done") String finalToken,
            @RequestBody @Valid MbrsJoinFinalizeVO body
    ) {
        mbrsJoinService.insertMbrsJoin(finalToken, body);
        return ResponseEntity.ok().build();
    }

    /**
     * 아이디 중복 확인
     *
     * tbhxzm101 HXZ_회원정보
     *
     *
     * [process]
     * 1. HXZ_회원정보 테이블 내의 회원 ID 조회
     * 2. 존재 할 시, 에러 팝업
     *
     * @param req
     * @return
     */
    @PostMapping("/checkId")
    @ResponseBody
    public ResponseEntity<?> readMbrsCountById(
            @RequestBody @Valid CheckIdReqVO req
    ) {
        boolean isCheckId = mbrsJoinService.readMbrsCountById(req.getMbrsId());
        if (isCheckId) {
            return ResponseEntity.ok("아이디가 이미 존재합니다.");
        }
        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }


    /**
     * 회원 가입 화면 조회 step4 + 회원 정보 추가
     *
     * tbhxzm101 HXZ_회원정보
     * tbhxzh101 HXZ_회원정보변경이력
     *
     * [process]
     * 1. HXZ_회원정보 테이블 내의 회원 정보 추가
     * 2. HXZ_회원정보변경이력 테이블 회원정보 변경 이력 추가
     * 3. 회원 가입 화면 step4 조회
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/step4.do")
    public String mbrsJoinStep4(
            @CookieValue(value="onb_done", required=false) String finalToken,
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        mbrsJoinService.mbrsJoinComplete(finalToken, req, res);
        return "/hxz/etc/mbrsjoin/step4";
    }
}
