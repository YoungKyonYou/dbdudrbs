package tmoney.co.kr.hxz.mypage.acntmng.service;

import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngInstReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngRspVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.TpwMbrsSvcVO;

import java.util.List;

public interface AcntMngService {
    PageData<AcntMngRspVO> readAcntMngPaging(AcntMngReqVO req, String mbrsId);

    AcntMngRspVO readPrsAcntMng(AcntMngReqVO req, String mbrsId);

    void acntMng(AcntMngInstReqVO req, String mbrsId);

    List<TpwMbrsSvcVO> mbrsSvcList(String mbrsSvcSta, String mbrsId);

    void updateMbrsSvc(TpwMbrsSvcVO req, String mbrsId);

    void insertMbrsSvcHist(TpwMbrsSvcVO req, String mbrsId);
}
