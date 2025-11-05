package tmoney.co.kr.hxz.spfnapl.sprtbzjoin.service;

import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzReqVO;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzRspVO;

import java.util.List;

public interface SprtBzJoinService {
    List<SprtBzRspVO> readSprtBz(SprtBzReqVO req);
}
