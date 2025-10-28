package tmoney.co.kr.hxz.mypage.mbrsinf.mapper;

import org.apache.ibatis.annotations.Mapper;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;

@HxzDb
@Mapper
public interface MbrsInfMapper {
    MbrsInfRspVO readMbrsInf(String mbrsId);

    void updateMbrsInf(MbrsInfReqVO vo);
}
