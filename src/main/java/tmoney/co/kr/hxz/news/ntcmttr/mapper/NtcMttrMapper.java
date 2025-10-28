package tmoney.co.kr.hxz.news.ntcmttr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrRspVO;
import tmoney.co.kr.hxz.news.ntcmttr.vo.NtcMttrSrchReqVO;

import java.util.List;

@HxzDb
@Mapper
public interface NtcMttrMapper {
    List<NtcMttrRspVO> readNtcMttrList(
            @Param("req") NtcMttrSrchReqVO req
    );

    long readNtcMttrListCnt(@Param("req") NtcMttrSrchReqVO req);

    NtcMttrRspVO readNtcMttrDtl(@Param("bltnNo") String bltnNo);
}
