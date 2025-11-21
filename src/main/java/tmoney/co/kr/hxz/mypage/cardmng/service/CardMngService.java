package tmoney.co.kr.hxz.mypage.cardmng.service;

import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngInstReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngRspVO;

public interface CardMngService {
    PageData<CardMngRspVO> readCardMngPaging(CardMngReqVO req, String mbrsId);

    CardMngRspVO readPrsCardMng(CardMngReqVO req, String mbrsId);

    void cardMng(CardMngInstReqVO req, String mbrsId);
}
