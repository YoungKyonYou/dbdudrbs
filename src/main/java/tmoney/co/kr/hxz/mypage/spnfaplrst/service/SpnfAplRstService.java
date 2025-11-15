package tmoney.co.kr.hxz.mypage.spnfaplrst.service;

import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstRspVO;

import java.util.List;

public interface SpnfAplRstService {
    List<SpnfAplRstRspVO> readSpnfAplRst(SpnfAplRstReqVO req, String mbrsId);
}
