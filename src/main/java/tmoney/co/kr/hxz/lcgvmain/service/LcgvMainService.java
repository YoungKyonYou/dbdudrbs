package tmoney.co.kr.hxz.lcgvmain.service;

import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainReqVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainRspVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvNtcRspVO;

import java.util.List;

public interface LcgvMainService {
    LcgvMainRspVO lcgvMain(LcgvMainReqVO req, String mbrsId);

    List<LcgvNtcRspVO> readLcgvNtcList(String tpwSvcId);
}
