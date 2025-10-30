package tmoney.co.kr.hxz.etc.mbrsjoin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinReqVO;

@HxzDb
@Mapper
public interface MbrsJoinMapper {
    void insertMbrsJoin(@Param("req") MbrsJoinReqVO req);

    int readMbrsCountById(@Param("checkId") String checkId);
}
