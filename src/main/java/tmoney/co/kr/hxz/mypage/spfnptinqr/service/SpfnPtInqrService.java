package tmoney.co.kr.hxz.mypage.spfnptinqr.service;

import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrRspVO;

import java.util.List;

public interface SpfnPtInqrService {
    PageData<SpfnPtInqrRspVO> readSpfnPtInqrPaging(SpfnPtInqrReqVO reqVO, String mbrsId);

    List<SpfnPtInqrRspVO> readSpfnPtInqr(SpfnPtInqrReqVO reqVO, String mbrsId);
}
