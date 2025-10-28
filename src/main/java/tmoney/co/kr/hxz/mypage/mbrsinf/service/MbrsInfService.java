package tmoney.co.kr.hxz.mypage.mbrsinf.service;

import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;

public interface MbrsInfService {
    MbrsInfRspVO readMbrsInf(String mbrsId);

    void updateMbrsInf(MbrsInfReqVO req);
}
