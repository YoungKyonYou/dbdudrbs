package tmoney.co.kr.hxz.mypage.spfnptinqr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spfnptinqr.vo.SpfnPtInqrRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface SpfnPtInqrMapper {
    List<SpfnPtInqrRspVO> readSpfnPtInqr(@Param("req") SpfnPtInqrReqVO req, @Param("mbrsId") String mbrsId);

    long readSpfnPtInqrCnt(@Param("req") SpfnPtInqrReqVO req, @Param("mbrsId") String mbrsId);
}
