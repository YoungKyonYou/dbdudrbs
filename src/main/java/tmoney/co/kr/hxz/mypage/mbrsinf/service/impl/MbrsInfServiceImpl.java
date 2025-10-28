package tmoney.co.kr.hxz.mypage.mbrsinf.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.mypage.mbrsinf.mapper.MbrsInfMapper;
import tmoney.co.kr.hxz.mypage.mbrsinf.service.MbrsInfService;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;

@RequiredArgsConstructor
@Service
public class MbrsInfServiceImpl implements MbrsInfService {
    private final MbrsInfMapper mbrsInfMapper;

    @Override
    @Transactional(readOnly = true)
    public MbrsInfRspVO readMbrsInf(String mbrsId) {
        return mbrsInfMapper.readMbrsInf(mbrsId);
    }

    @Override
    @Transactional
    public void updateMbrsInf(MbrsInfReqVO req) {
        mbrsInfMapper.updateMbrsInf(req);
    }
}
