package tmoney.co.kr.hxz.svcjoin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.svcjoin.vo.prevsvc.PrevSvcRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthInstReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinInstReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface SvcJoinMapper {
    OrgInfRspVO readOrgInf(@Param("req") OrgInfReqVO req);

    String readOrgCdByAddoCd(@Param("addoCd") String addoCd);

    List<PrevSvcRspVO> readSvcInf(@Param("tpwSvcId") String tpwSvcId);

    List<PrevSvcRspVO> readPrevSvcInf(@Param("mbrsId") String mbrsId);

    int insertRsdcAuth(RsdcAuthInstReqVO reqVO);

    List<SvcJoinRspVO> readSvcJoin(@Param("req") SvcJoinReqVO req);

    void insertSvcJoin(@Param("req") SvcJoinInstReqVO req, @Param("mbrsId") String mbrsId);
}
