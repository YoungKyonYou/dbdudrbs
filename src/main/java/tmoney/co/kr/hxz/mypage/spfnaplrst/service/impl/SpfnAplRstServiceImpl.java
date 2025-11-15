package tmoney.co.kr.hxz.mypage.spfnaplrst.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.mypage.spfnaplrst.mapper.SpfnAplRstMapper;
import tmoney.co.kr.hxz.mypage.spfnaplrst.service.SpfnAplRstService;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpfnAplRstServiceImpl implements SpfnAplRstService {
    private final SpfnAplRstMapper spfnAplRstMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SpfnAplRstRspVO> readSpfnAplRst(SpfnAplRstReqVO req, String mbrsId) {
        return spfnAplRstMapper.readSpfnAplRst(req, mbrsId);
    }
}
