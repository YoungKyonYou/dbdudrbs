package tmoney.co.kr.hxz.spfnapl.sprtbzjoin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzReqVO;
import tmoney.co.kr.hxz.spfnapl.sprtbzjoin.vo.SprtBzRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface SprtBzJoinMapper {
    List<SprtBzRspVO> readSprtBz(@Param("req") SprtBzReqVO req);
}
