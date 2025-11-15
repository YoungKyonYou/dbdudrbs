package tmoney.co.kr.hxz.mypage.utlzptinqr.service;

import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrRspVO;

import java.util.List;

public interface UtlzPtInqrService {
    PageData<UtlzPtInqrRspVO> readUtlzPtInqrPaging(UtlzPtInqrReqVO reqVO, String mbrsId);

    List<UtlzPtInqrRspVO> readUtlzPtInqr(UtlzPtInqrReqVO reqVO, String mbrsId);
}
