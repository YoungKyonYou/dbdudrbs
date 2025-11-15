package tmoney.co.kr.hxz.mypage.spnfptinqr.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.spnfptinqr.mapper.SpnfPtInqrMapper;
import tmoney.co.kr.hxz.mypage.spnfptinqr.service.SpnfPtInqrService;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpnfPtInqrServiceImpl implements SpnfPtInqrService {
    private final SpnfPtInqrMapper spnfPtInqrMapper;


    @Override
    @Transactional(readOnly = true)
    public PageData<SpnfPtInqrRspVO> readSpnfPtInqrPaging(SpnfPtInqrReqVO req, String mbrsId) {
        final int offset = req.getPage() * req.getSize();

        long total = readSpnfPtInqrCnt(req, mbrsId);

        SpnfPtInqrReqVO reqVO = new SpnfPtInqrReqVO(req.getTpwSvcId(), req.getPaySttDt(), req.getPayEndDt(), offset, req.getSize(), req.getSort(), req.getDir());
        List<SpnfPtInqrRspVO> content = readSpnfPtInqr(reqVO, mbrsId);

        return new PageData<>(content, req.getPage(), req.getSize(), total);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpnfPtInqrRspVO> readSpnfPtInqr(SpnfPtInqrReqVO req, String mbrsId) {
        return spnfPtInqrMapper.readSpnfPtInqr(req, mbrsId);
    }

    @Transactional(readOnly = true)
    public long readSpnfPtInqrCnt(SpnfPtInqrReqVO req, String mbrsId) {
        return spnfPtInqrMapper.readSpnfPtInqrCnt(req, mbrsId);
    }
}
