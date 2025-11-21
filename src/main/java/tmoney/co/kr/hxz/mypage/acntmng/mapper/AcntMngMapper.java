package tmoney.co.kr.hxz.mypage.acntmng.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.acntmng.vo.TpwMbrsSvcVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngInstReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface AcntMngMapper {
    List<AcntMngRspVO> readAcntMng(@Param("req") AcntMngReqVO req, @Param("mbrsId") String mbrsId);

    long readAcntMngCnt(@Param("req") AcntMngReqVO req, @Param("mbrsId") String mbrsId);

    void insertAcntMng(@Param("req") AcntMngInstReqVO req, @Param("mbrsId") String mbrsId);

    List<TpwMbrsSvcVO> readMbrsSvcList(@Param("tpwMbrsSvcStaCd") String tpwMbrsSvcStaCd, @Param("mbrsId") String mbrsId);

    void updateMbrsSvc(@Param("req") TpwMbrsSvcVO req);

    void insertMbrsSvcHist(@Param("req") TpwMbrsSvcVO req);
}
