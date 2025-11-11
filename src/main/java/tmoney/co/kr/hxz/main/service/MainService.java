package tmoney.co.kr.hxz.main.service;

import tmoney.co.kr.hxz.main.vo.MainNtcRspVO;
import tmoney.co.kr.hxz.main.vo.MainSvcRspVO;
import tmoney.co.kr.hxz.main.vo.MySvcRspVO;
import tmoney.co.kr.hxz.main.vo.OrgInfRspVO;

import java.util.List;

public interface MainService {
    List<MainNtcRspVO> readMainNtcList();

    List<MySvcRspVO> readMySvcList(String mbrsId);

    List<MainSvcRspVO> readMainSvcList(String mbrsId);

    List<OrgInfRspVO> readOrgInfList();
}
