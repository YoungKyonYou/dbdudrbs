package tmoney.co.kr.hxz.svcjoin.service;

import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svccncn.SvcCncnReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.BankCdRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinInstReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfRspVO;

import java.util.List;

public interface SvcJoinService {
    OrgInfRspVO readOrgInf(OrgInfReqVO req);

    String rsdcAuth(RsdcAuthReqVO req, String mbrsId);

    void svcCncn(SvcCncnReqVO req, String mbrsId);

    List<SvcJoinRspVO> readSvcJoin(SvcJoinReqVO req);

    void svcJoin(SvcJoinInstReqVO req, String mbrsId);

    List<BankCdRspVO> readCmnBankCdList();
}
