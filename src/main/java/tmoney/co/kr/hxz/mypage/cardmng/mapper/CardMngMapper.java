package tmoney.co.kr.hxz.mypage.cardmng.mapper;

import org.apache.ibatis.annotations.Mapper;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngInstReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface CardMngMapper {
    List<CardMngRspVO> readCardMng(CardMngReqVO req, String mbrsId);

    long readCardMngCnt(CardMngReqVO req, String mbrsId);

    void insertCardMng(CardMngInstReqVO req, String mbrsId);
}
