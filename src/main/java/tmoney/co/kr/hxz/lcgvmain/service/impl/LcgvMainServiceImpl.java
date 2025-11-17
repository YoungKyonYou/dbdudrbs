package tmoney.co.kr.hxz.lcgvmain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.lcgvmain.mapper.LcgvMainMapper;
import tmoney.co.kr.hxz.lcgvmain.service.LcgvMainService;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainReqVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainRspVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvNtcRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LcgvMainServiceImpl implements LcgvMainService {
    private final LcgvMainMapper lcgvMainMapper;

    @Override
    @Transactional(readOnly = true)
    public LcgvMainRspVO readLcgvMain(LcgvMainReqVO req, String mbrsId) {
        LcgvMainRspVO rsp = lcgvMainMapper.readLcgvMain(req, mbrsId);
        rsp.setUrl("/skin/jongro/page/main/index");
        return rsp;
    };

    @Override
    @Transactional(readOnly = true)
    public List<LcgvNtcRspVO> readLcgvNtcList(String tpwSvcId) {
        return lcgvMainMapper.readLcgvNtcList(tpwSvcId);
    };
}
