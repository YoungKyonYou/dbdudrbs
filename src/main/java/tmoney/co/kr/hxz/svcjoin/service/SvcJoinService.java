package tmoney.co.kr.hxz.svcjoin.service;

import org.springframework.web.multipart.MultipartFile;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.CmnRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.svccncn.SvcCncnReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.*;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfRspVO;

import java.util.List;

public interface SvcJoinService {
    OrgInfRspVO readOrgInf(OrgInfReqVO req);

    CmnRspVO<RsdcAuthRspVO> rsdcAuth(RsdcAuthReqVO req, String mbrsId);

    void svcPrevCncn(SvcCncnReqVO req, String mbrsId);

    List<SvcJoinRspVO> readSvcJoin(SvcJoinReqVO req);

    void svcJoin(RsdcInfReqVO rsdcInfReqVO, SvcJoinInstReqVO req, MultipartFile file, String mbrsId);

    List<BankCdRspVO> readCmnBankCdList();
}
