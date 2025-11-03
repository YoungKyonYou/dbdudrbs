package tmoney.co.kr.hxz.etc.idsrch.service;

import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchReqVO;
import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchRspVO;

public interface IdSrchService {
    IdSrchRspVO findMbrsId(IdSrchReqVO req);
}
