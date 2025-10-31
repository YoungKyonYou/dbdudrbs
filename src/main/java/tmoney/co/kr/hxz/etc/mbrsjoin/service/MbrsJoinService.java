package tmoney.co.kr.hxz.etc.mbrsjoin.service;

import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;

public interface MbrsJoinService {
    void insertMbrsJoin(MbrsJoinInstReqVO req);

    boolean readMbrsCountById(String checkId);
}
