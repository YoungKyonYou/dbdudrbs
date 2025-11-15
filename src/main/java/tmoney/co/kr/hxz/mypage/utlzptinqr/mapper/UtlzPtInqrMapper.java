package tmoney.co.kr.hxz.mypage.utlzptinqr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.utlzptinqr.vo.UtlzPtInqrRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface UtlzPtInqrMapper {
    List<UtlzPtInqrRspVO> readUtlzPtInqr(@Param("req") UtlzPtInqrReqVO req, @Param("mbrsId") String mbrsId);

    long readUtlzPtInqrCnt(@Param("req") UtlzPtInqrReqVO req, @Param("mbrsId") String mbrsId);
}
