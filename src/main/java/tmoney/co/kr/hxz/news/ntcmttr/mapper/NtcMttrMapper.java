package tmoney.co.kr.hxz.news.ntcmttr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzMapper;
import tmoney.co.kr.hxz.news.ntcmttr.vo.RspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.SrchReqVO;

import java.util.List;

@HxzMapper
@Mapper
public interface NtcMttrMapper {
    List<RspVO> readNtcMttrList(
            @Param("req") SrchReqVO req
    );

    long readNtcMttrListCnt(@Param("req") SrchReqVO req);
}
