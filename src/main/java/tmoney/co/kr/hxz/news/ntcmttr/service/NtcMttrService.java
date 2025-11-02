package tmoney.co.kr.hxz.news.ntcmttr.service;

import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrRspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrSrchReqVO;

import java.util.List;

public interface NtcMttrService {
    PageDataVO<NtcMttrRspVO> readNtcMttrPaging(NtcMttrSrchReqVO req);

    List<NtcMttrRspVO> readNtcMttrList(NtcMttrSrchReqVO req);

    long readNtcMttrListCnt(NtcMttrSrchReqVO req);

    NtcMttrRspVO readNtcMttrDtl(String bltnNo);

    NtcMttrRspVO readPrevNtcMttr(String bltnNo);

    NtcMttrRspVO readNextNtcMttr(String bltnNo);

    void updateNtcInqrNcnt(String bltnNo);
}
