package tmoney.co.kr.hxz.common.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController2 {

    // ===== 공지사항 =====
    @GetMapping("/notice/boardList")
    public String boardList(Model model) {

        model.addAttribute("breadcrumb", List.of("홈", "공지사항"));

        List<Map<String, Object>> noticeList = List.of(
                Map.of("notice", true, "title", "SKT 해킹사고에 따른 유심 무상교체 시 카드번호 재등록 안내입니다.", "date", "2025.05.07", "link", "#"),
                Map.of("notice", true, "title", "2025년 07월 11일 SKT 휴대폰 본인확인 시스템 작업 공지 안내입니다.", "date", "2025.05.07", "link", "#"),
                Map.of("notice", false, "num", 83, "title", "LGU+ 휴대폰 본인확인 서비스 PASS인증 시스템 작업 공지 안내", "date", "2025.05.07", "link", "#"),
                Map.of("notice", false, "num", 82, "title", "회원가입 시 OO은행 계좌 유효성 체크 장애 발생 안내", "date", "2025.04.07", "link", "#"),
                Map.of("notice", false, "num", 81, "title", "티머니 공식 홈페이지 리뉴얼 오픈 안내", "date", "2025.02.24", "link", "#"),
                Map.of("notice", false, "num", 80, "title", "개인정보 처리방침 개정 안내", "date", "2025.02.24", "link", "#"),
                Map.of("notice", false, "num", 79, "title", "일일 시스템 점검 안내", "date", "2025.02.24", "link", "#"),
                Map.of("notice", false, "num", 78, "title", "본인확인서비스(카카오뱅크) 일시 중지 안내", "date", "2025.02.24", "link", "#"),
                Map.of("notice", false, "num", 77, "title", "클라우드 관련 작업 공지 안내", "date", "2025.02.24", "link", "#"),
                Map.of("notice", false, "num", 76, "title", "일일 시스템 점검 안내", "date", "2025.02.24", "link", "#")
        );

        model.addAttribute("noticeList", noticeList);

        return "page/notice/boardList";
    }


    @GetMapping("/notice/boardDetail")
    public String boardDetail(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "공지사항", "상세보기"));
        return "page/notice/boardDetail";
    }

    // ===== 가이드 =====
    @GetMapping("/guide/guide_join")
    public String guideJoin(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "가이드", "회원가입 안내"));
        return "page/guide/guide_join";
    }

    @GetMapping("/guide/guide_subsidy")
    public String guideSubsidy(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "가이드", "지원금 안내"));
        return "page/guide/guide_subsidy";
    }

    // ===== 소개 =====
    @GetMapping("/intro/intro")
    public String intro(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "소개"));
        return "page/intro/intro";
    }

    @GetMapping("/intro/model")
    public String model(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "소개", "모델소개"));
        return "page/intro/model";
    }

    @GetMapping("/intro/business")
    public String business(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "소개", "사업소개"));
        return "page/intro/business";
    }

    // ===== 서비스 =====
    @GetMapping("/service/service")
    public String service(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "서비스"));
        return "page/service/service";
    }

    // ===== 로그인 =====
    @GetMapping("/login")
    public String login() {
        return "page/login/login";
    }

    @GetMapping("/login/find_id")
    public String findId() {
        return "page/login/find_id";
    }

    @GetMapping("/login/find_id2")
    public String findId2() {
        return "page/login/find_id2";
    }

    @GetMapping("/login/find_pw")
    public String findPw() {
        return "page/login/find_pw";
    }

    @GetMapping("/login/find_pw2")
    public String findPw2() {
        return "page/login/find_pw2";
    }

    // ===== 회원가입 =====
    @GetMapping("/join/step1")
    public String joinStep1() {
        return "page/join/step1";
    }

    @GetMapping("/join/step2")
    public String joinStep2() {
        return "page/join/step2";
    }

    @GetMapping("/join/step3")
    public String joinStep3() {
        return "page/join/step3";
    }

    @GetMapping("/join/step4")
    public String joinStep4() {
        return "page/join/step4";
    }

    // ===== 지원사업 =====
    @GetMapping("/supportpjt/step1")
    public String supportStep1() {
        return "page/supportpjt/step1";
    }

    @GetMapping("/supportpjt/step1_modal1")
    public String supportStep1Modal1() {
        return "page/supportpjt/step1_modal1";
    }

    @GetMapping("/supportpjt/step1_modal2")
    public String supportStep1Modal2() {
        return "page/supportpjt/step1_modal2";
    }

    @GetMapping("/supportpjt/step2")
    public String supportStep2() {
        return "page/supportpjt/step2";
    }

    @GetMapping("/supportpjt/step3")
    public String supportStep3() {
        return "page/supportpjt/step3";
    }

    // ===== 마이페이지 =====
    @GetMapping("/mypage/info")
    public String myInfo(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "내 정보"));
        return "page/mypage/info";
    }

    @GetMapping("/mypage/change_pw")
    public String changePw(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "비밀번호 변경"));
        return "page/mypage/change_pw";
    }

    @GetMapping("/mypage/withdrawal")
    public String withdrawal(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "회원 탈퇴"));
        return "page/mypage/withdrawal";
    }

    @GetMapping("/side_mypage/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "대시보드"));
        return "page/side_mypage/dashboard";
    }

//    @GetMapping("/side_mypage/namefix")
//    public String nameFix(Model model) {
//        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "이름 변경"));
//        return "page/side_mypage/namefix";
//    }


    @GetMapping("/side_mypage/myside")
    public String mySide(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "내 정보"));
        return "page/side_mypage/myside";
    }

    @GetMapping("/side_mypage/result")
    public String result(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "결과"));
        return "page/side_mypage/result";
    }

    @GetMapping("/side_mypage/history_payment")
    public String historyPayment(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "결제 내역"));
        return "page/side_mypage/history_payment";
    }

    @GetMapping("/side_mypage/history_details")
    public String historyDetails(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "결제 상세"));
        model.addAttribute("contentFragment", "page/side_mypage/history_details :: historyDetailsContent");
        return "page/side_mypage/side_mypage_layout";
    }

    @GetMapping("/side_mypage/namefix")
    public String nameFix(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "이름 변경"));
        model.addAttribute("title", "이름 변경");
        model.addAttribute("contentFragment", "page/side_mypage/namefix :: namefixContent");
        return "page/side_mypage/side_mypage_layout";
    }


    @GetMapping("/side_mypage/mng_card")
    public String manageCard(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "카드 관리"));
        return "page/side_mypage/mng_card";
    }

    @GetMapping("/side_mypage/mng_account")
    public String manageAccount(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "마이페이지", "계좌 관리"));
        return "page/side_mypage/mng_account";
    }

    // ===== FAQ =====
    @GetMapping("/side_faq/faq")
    public String faq(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "FAQ"));
        return "page/side_faq/faq";
    }


    // ===== 이용약관 =====
    @GetMapping("/terms/privacy")
    public String terms(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "이용약관"));
        return "page/terms/privacy";
    }

    // ===== 지원금 신청 =====
    @GetMapping("/side_applygrant/applygrant")
    public String applyGrant(Model model) {
        model.addAttribute("breadcrumb", List.of("홈", "지원금 신청"));
        return "page/side_applygrant/applygrant";
    }
}