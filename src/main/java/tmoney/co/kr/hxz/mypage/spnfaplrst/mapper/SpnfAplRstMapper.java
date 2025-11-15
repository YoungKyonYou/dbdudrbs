package tmoney.co.kr.hxz.mypage.spnfaplrst.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstReqVO;
import tmoney.co.kr.hxz.mypage.spnfaplrst.vo.SpnfAplRstRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface SpnfAplRstMapper {
    List<SpnfAplRstRspVO> readSpnfAplRst(@Param("req") SpnfAplRstReqVO req, @Param("mbrsId") String mbrsId);
}
