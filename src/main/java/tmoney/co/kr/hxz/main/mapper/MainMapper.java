package tmoney.co.kr.hxz.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.main.vo.MainNtcRspVO;
import tmoney.co.kr.hxz.main.vo.MainSvcRspVO;
import tmoney.co.kr.hxz.main.vo.MySvcRspVO;
import tmoney.co.kr.hxz.main.vo.OrgInfRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface MainMapper {
    List<MainNtcRspVO> readMainNtcList();

    List<MySvcRspVO> readMySvcList(@Param("mbrsId") String mbrsId);

    List<MainSvcRspVO> readMainSvcList(@Param("mbrsId") String mbrsId);

    List<OrgInfRspVO> readOrgInfList(@Param("mbrsId") String mbrsId);
}
