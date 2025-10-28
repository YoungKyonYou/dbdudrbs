package tmoney.co.kr.hxz.news.ntcmttr.service;

import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.RspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.SrchReqVO;

import java.util.List;

public interface NtcMttrService {
    PageDataVO<RspVO> readNtcMttrPaging(SrchReqVO req);

    List<RspVO> readNtcMttrList(SrchReqVO req);

    long readNtcMttrListCnt(SrchReqVO req);

    RspVO readNtcMttrDtl(String bltnNo);
}
