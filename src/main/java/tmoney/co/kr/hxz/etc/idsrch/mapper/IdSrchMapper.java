package tmoney.co.kr.hxz.etc.idsrch.mapper;

import org.apache.ibatis.annotations.Mapper;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchReqVO;
import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchRspVO;

@HxzDb
@Mapper
public interface IdSrchMapper {
    IdSrchRspVO findMbrsId(IdSrchReqVO req);
}
