package tmoney.co.kr.hxz.mypage.spfnaplrst.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spfnaplrst.vo.SpfnAplRstRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface SpfnAplRstMapper {
    List<SpfnAplRstRspVO> readSpfnAplRst(@Param("req") SpfnAplRstReqVO req, @Param("mbrsId") String mbrsId);
}
