package tmoney.co.kr.hxz.mypage.spnfptinqr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrReqVO;
import tmoney.co.kr.hxz.mypage.spnfptinqr.vo.SpnfPtInqrRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface SpnfPtInqrMapper {
    List<SpnfPtInqrRspVO> readSpnfPtInqr(@Param("req") SpnfPtInqrReqVO req, @Param("mbrsId") String mbrsId);

    long readSpnfPtInqrCnt(@Param("req") SpnfPtInqrReqVO req, @Param("mbrsId") String mbrsId);
}
