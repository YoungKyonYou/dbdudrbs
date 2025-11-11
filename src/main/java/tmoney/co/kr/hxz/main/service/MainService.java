package tmoney.co.kr.hxz.main.service;

import tmoney.co.kr.hxz.main.vo.MainNtcRspVO;
import tmoney.co.kr.hxz.main.vo.MainSvcRspVO;

import java.util.List;

public interface MainService {
    List<MainNtcRspVO> readMainNtcList();

    List<MainSvcRspVO> readMainSvcList(String mbrsId);

    List<MainSvcRspVO> readMainSvcList();
}
