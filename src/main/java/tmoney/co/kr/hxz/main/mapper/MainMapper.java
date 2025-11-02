package tmoney.co.kr.hxz.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.main.vo.MainNtcRspVO;

import java.util.List;

@HxzDb
@Mapper
public interface MainMapper {
    List<MainNtcRspVO> readMainNtcList();
}
