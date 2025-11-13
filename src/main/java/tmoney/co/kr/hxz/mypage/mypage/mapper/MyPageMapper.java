package tmoney.co.kr.hxz.mypage.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvReqVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyPageRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.apl.MyAplRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface MyPageMapper {
    MyPageRspVO readMyPage(@Param("req") MyLcgvReqVO req, @Param("mbrsId") String mbrsId);

    MyAplRspVO readMyApl(@Param("req") MyLcgvReqVO req, @Param("mbrsId") String mbrsId);

    List<MyLcgvRspVO> readMyLcgv(@Param("req") MyLcgvReqVO req, @Param("mbrsId") String mbrsId);
}
