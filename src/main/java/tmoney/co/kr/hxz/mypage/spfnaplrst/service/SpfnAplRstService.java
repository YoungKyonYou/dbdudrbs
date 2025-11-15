package tmoney.co.kr.hxz.mypage.spfnaplrst.service;

import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstRspVO;

import java.util.List;

public interface SpfnAplRstService {
    List<SpfnAplRstRspVO> readSpfnAplRst(SpfnAplRstReqVO req, String mbrsId);
}
