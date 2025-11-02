package tmoney.co.kr.hxz.etc.mbrsjoin.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import tmoney.co.kr.hxz.common.onboard.vo.SignupVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.rcpt.MbrsJoinFinalizeVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MbrsJoinService {

    Map<String, String> submitAuthResult(@CookieValue("onb") String token,
                                         @RequestHeader("X-Nonce") String nonce,
                                         HttpServletRequest req,
                                         HttpServletResponse res);

    Map<String, String> submitInfResult(@CookieValue("onb") String token,
                                        @RequestHeader("X-Nonce") String nonce,
                                        MbrsJoinInstReqVO payload,
                                        HttpServletRequest req,
                                        HttpServletResponse res);

    void start(HttpServletRequest req, HttpServletResponse res);

    SignupVO mbrsJoinStep2(HttpServletRequest req, Model model);

    void insertMbrsJoin(String finalToken, MbrsJoinFinalizeVO body);

    boolean readMbrsCountById(String checkId);
}
