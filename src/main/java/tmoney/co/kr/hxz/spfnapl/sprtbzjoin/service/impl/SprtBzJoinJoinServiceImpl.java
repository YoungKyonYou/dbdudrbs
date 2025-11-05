package tmoney.co.kr.hxz.spfnapl.sprtbzjoin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.mapper.SprtBzJoinMapper;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.service.SprtBzJoinService;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzReqVO;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SprtBzJoinJoinServiceImpl implements SprtBzJoinService {
    private final SprtBzJoinMapper sprtBzJoinMapper;

    public List<SprtBzRspVO> readSprtBz(SprtBzReqVO req) {
        return sprtBzJoinMapper.readSprtBz(req);
    }
}
