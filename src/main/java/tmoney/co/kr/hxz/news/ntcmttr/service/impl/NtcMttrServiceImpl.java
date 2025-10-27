package tmoney.co.kr.hxz.news.ntcmttr.service.impl;

import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.ntcmttr.mapper.NtcMttrMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.news.ntcmttr.service.NtcMttrService;
import tmoney.co.kr.hxz.news.ntcmttr.vo.RspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.SrchReqVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NtcMttrServiceImpl implements NtcMttrService {
    private final NtcMttrMapper ntcMttrMapper;

    @Override
    @Transactional(readOnly = true)
    public PageDataVO<RspVO> readNtcMttrPaging(SrchReqVO req) {
        final int offset = req.getPage() * req.getSize();

        long total = readNtcMttrListCnt(req);

        SrchReqVO reqVO = new SrchReqVO(req.getOrgCd(), req.getNtcMttrTtlNm(), req.getNtcMttrCtt(), offset, req.getSize(), req.getSort(), req.getDir());

        List<RspVO> content = readNtcMttrList(reqVO);

        return new PageDataVO<>(content, req.getPage(), req.getSize(), total);
    };

    @Override
    @Transactional(readOnly = true)
    public List<RspVO> readNtcMttrList(SrchReqVO req) {
        return ntcMttrMapper.readNtcMttrList(req);
    };

    @Override
    @Transactional(readOnly = true)
    public long readNtcMttrListCnt(SrchReqVO req) {
        return ntcMttrMapper.readNtcMttrListCnt(req);
    };

}
