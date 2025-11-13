package tmoney.co.kr.hxz.lcgvmain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainReqVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvMainRspVO;
import tmoney.co.kr.hxz.lcgvmain.vo.LcgvNtcRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface LcgvMainMapper {
    LcgvMainRspVO readLcgvMain(@Param("req") LcgvMainReqVO req, @Param("mbrsId") String mbrsId);

    List<LcgvNtcRspVO> readLcgvNtcList(@Param("tpwSvcId") String tpwSvcId);
}
