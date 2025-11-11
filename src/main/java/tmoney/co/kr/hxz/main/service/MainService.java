package tmoney.co.kr.hxz.main.service;

import tmoney.co.kr.hxz.main.vo.*;
import tmoney.co.kr.hxz.main.vo.lcgv.LcgvMainRspVO;

import java.util.List;

public interface MainService {
    List<MainNtcRspVO> readMainNtcList();

    List<MySvcRspVO> readMySvcList(String mbrsId);

    List<MainSvcRspVO> readMainSvcList(String mbrsId);

    List<OrgInfRspVO> readOrgInfList(String mbrsId);

    LcgvMainRspVO readLcgvMain(String mbrsId);
}
