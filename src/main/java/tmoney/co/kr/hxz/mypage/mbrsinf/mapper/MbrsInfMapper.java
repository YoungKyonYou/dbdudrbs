package tmoney.co.kr.hxz.mypage.mbrsinf.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsUpdReqVO;

@HxzDb
@Mapper
public interface MbrsInfMapper {
    MbrsInfRspVO readMbrsInf(String mbrsId);

    void updateMbrsInf(@Param("req") MbrsUpdReqVO req);

    void insertMbrsInf(@Param("req") MbrsInfReqVO req);

    void updatePwd(@Param("mbrsId") String mbrsId, @Param("newPwd") String newPwd);
}
