package tmoney.co.kr.hxz.etc.mbrsinf.service;

import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsInfDtlRspVO;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsInfRspVO;
import tmoney.co.kr.hxz.etc.mbrsinf.vo.MbrsUpdReqVO;

public interface MbrsInfService {
    MbrsInfDtlRspVO mbrsInf(String mbrsId);

    void updateMbrsInf(MbrsUpdReqVO req, String mbrsId);

    void insertMbrsInf(MbrsInfReqVO req);

    void updatePwd(String mbrsId, String newPwd, String cfmPwd);
}
