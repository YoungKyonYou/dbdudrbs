package tmoney.co.kr.hxz.mypage.spnfptinqr.service;

import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrRspVO;

import java.util.List;

public interface SpnfPtInqrService {
    PageData<SpnfPtInqrRspVO> readSpnfPtInqrPaging(SpnfPtInqrReqVO reqVO, String mbrsId);

    List<SpnfPtInqrRspVO> readSpnfPtInqr(SpnfPtInqrReqVO reqVO, String mbrsId);
}
