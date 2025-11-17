package tmoney.co.kr.hxz.mypage.utlzptinqr.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.utlzptinqr.mapper.UtlzPtInqrMapper;
import tmoney.co.kr.hxz.mypage.utlzptinqr.service.UtlzPtInqrService;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UtlzPtInqrServiceImpl implements UtlzPtInqrService {
    private final UtlzPtInqrMapper utlzPtInqrMapper;

    @Override
    @Transactional(readOnly = true)
    public PageData<UtlzPtInqrRspVO> readUtlzPtInqrPaging(UtlzPtInqrReqVO req, String mbrsId) {
        final int offset = req.getPage() * req.getSize();

        long total = readUtlzPtInqrCnt(req, mbrsId);

        UtlzPtInqrReqVO reqVO = new UtlzPtInqrReqVO(req.getTpwSvcId(), req.getStlmDt(), offset, req.getSize(), req.getSort(), req.getDir());
        List<UtlzPtInqrRspVO> content = readUtlzPtInqr(reqVO, mbrsId);

        return new PageData<>(content, req.getPage(), req.getSize(), total);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtlzPtInqrRspVO> readUtlzPtInqr(UtlzPtInqrReqVO req, String mbrsId) {
        return utlzPtInqrMapper.readUtlzPtInqr(req, mbrsId);
    }

    @Transactional(readOnly = true)
    public long readUtlzPtInqrCnt(UtlzPtInqrReqVO req, String mbrsId) {
        return utlzPtInqrMapper.readUtlzPtInqrCnt(req, mbrsId);
    }
}
