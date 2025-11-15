package tmoney.co.kr.hxz.mypage.spnfaplrst.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.mypage.spnfaplrst.mapper.SpnfAplRstMapper;
import tmoney.co.kr.hxz.mypage.spnfaplrst.service.SpnfAplRstService;
import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpnfAplRstServiceImpl implements SpnfAplRstService {
    private final SpnfAplRstMapper spnfAplRstMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SpnfAplRstRspVO> readSpnfAplRst(SpnfAplRstReqVO req, String mbrsId) {
        return spnfAplRstMapper.readSpnfAplRst(req, mbrsId);
    }
}
