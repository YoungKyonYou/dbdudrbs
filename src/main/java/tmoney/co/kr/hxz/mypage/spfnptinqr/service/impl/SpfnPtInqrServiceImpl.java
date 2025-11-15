package tmoney.co.kr.hxz.mypage.spfnptinqr.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.spfnptinqr.mapper.SpfnPtInqrMapper;
import tmoney.co.kr.hxz.mypage.spfnptinqr.service.SpfnPtInqrService;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpfnPtInqrServiceImpl implements SpfnPtInqrService {
    private final SpfnPtInqrMapper spfnPtInqrMapper;


    @Override
    @Transactional(readOnly = true)
    public PageData<SpfnPtInqrRspVO> readSpfnPtInqrPaging(SpfnPtInqrReqVO req, String mbrsId) {
        final int offset = req.getPage() * req.getSize();

        long total = readSpfnPtInqrCnt(req, mbrsId);

        SpfnPtInqrReqVO reqVO = new SpfnPtInqrReqVO(req.getTpwSvcId(), req.getPaySttDt(), req.getPayEndDt(), offset, req.getSize(), req.getSort(), req.getDir());
        List<SpfnPtInqrRspVO> content = readSpfnPtInqr(reqVO, mbrsId);

        return new PageData<>(content, req.getPage(), req.getSize(), total);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpfnPtInqrRspVO> readSpfnPtInqr(SpfnPtInqrReqVO req, String mbrsId) {
        return spfnPtInqrMapper.readSpfnPtInqr(req, mbrsId);
    }

    @Transactional(readOnly = true)
    public long readSpfnPtInqrCnt(SpfnPtInqrReqVO req, String mbrsId) {
        return spfnPtInqrMapper.readSpfnPtInqrCnt(req, mbrsId);
    }
}
