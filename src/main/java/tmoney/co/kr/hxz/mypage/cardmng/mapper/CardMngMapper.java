package tmoney.co.kr.hxz.mypage.cardmng.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngInstReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface CardMngMapper {
    List<CardMngRspVO> readCardMng(@Param("req") CardMngReqVO req, @Param("mbrsId") String mbrsId);

    long readCardMngCnt(@Param("req") CardMngReqVO req, @Param("mbrsId") String mbrsId);

    void insertCardMng(@Param("req") CardMngInstReqVO req, @Param("mbrsId") String mbrsId);
}
