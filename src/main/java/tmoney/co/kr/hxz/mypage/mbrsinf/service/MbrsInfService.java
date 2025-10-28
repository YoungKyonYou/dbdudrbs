package tmoney.co.kr.hxz.mypage.mbrsinf.service;

import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsUpdReqVO;

public interface MbrsInfService {
    MbrsInfRspVO readMbrsInf(String mbrsId);

    void updateMbrsInf(MbrsUpdReqVO req);

    void insertMbrsInf(MbrsInfReqVO req);

    void updatePwd(String mbrsId, String newPwd, String cfmPwd);
}
