package tmoney.co.kr.hxz.news.ntcmttr.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.ntcmttr.mapper.NtcMttrMapper;
import tmoney.co.kr.hxz.news.ntcmttr.service.NtcMttrService;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrRspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrSrchReqVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NtcMttrServiceImpl implements NtcMttrService {
    private final NtcMttrMapper ntcMttrMapper;

    @Override
    @Transactional(readOnly = true)
    public PageDataVO<NtcMttrRspVO> readNtcMttrPaging(NtcMttrSrchReqVO req) {
        final int offset = req.getPage() * req.getSize();

        long total = readNtcMttrListCnt(req);

        NtcMttrSrchReqVO reqVO = new NtcMttrSrchReqVO(req.getOrgCd(), req.getNtcMttrTtlNm(), req.getNtcMttrCtt(), offset, req.getSize(), req.getSort(), req.getDir());

        List<NtcMttrRspVO> content = readNtcMttrList(reqVO);

        return new PageDataVO<>(content, req.getPage(), req.getSize(), total);
    };

    @Override
    @Transactional(readOnly = true)
    public List<NtcMttrRspVO> readNtcMttrList(NtcMttrSrchReqVO req) {
        return ntcMttrMapper.readNtcMttrList(req);
    };

    @Override
    @Transactional(readOnly = true)
    public long readNtcMttrListCnt(NtcMttrSrchReqVO req) {
        return ntcMttrMapper.readNtcMttrListCnt(req);
    };

    @Override
    @Transactional(readOnly = true)
    public NtcMttrRspVO readNtcMttrDtl(String bltnNo) {
        return ntcMttrMapper.readNtcMttrDtl(bltnNo);
    }

    @Override
    @Transactional
    public void updateNtcInqrNcnt(String bltnNo) {
        ntcMttrMapper.updateNtcInqrNcnt(bltnNo);
    }

    @Override
    @Transactional(readOnly = true)
    public NtcMttrRspVO readPrevNtcMttr(String bltnNo) {
        return ntcMttrMapper.readPrevNtcMttr(bltnNo);
    }

    @Override
    @Transactional(readOnly = true)
    public NtcMttrRspVO readNextNtcMttr(String bltnNo) {
        return ntcMttrMapper.readNextNtcMttr(bltnNo);
    }
}
