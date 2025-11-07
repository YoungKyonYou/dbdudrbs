package tmoney.co.kr.hxz.svcjoin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.svcjoin.mapper.SvcJoinMapper;
import tmoney.co.kr.hxz.svcjoin.service.SvcJoinService;
import tmoney.co.kr.hxz.svcjoin.vo.addo.AddoCdReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.prevsvc.PrevSvcRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.svccncn.SvcCncnReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.SvcJoinRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SvcJoinServiceImpl implements SvcJoinService {
    private final SvcJoinMapper svcJoinMapper;

    @Override
    @Transactional(readOnly = true)
    public OrgInfRspVO readOrgInf(OrgInfReqVO req) {
        return svcJoinMapper.readOrgInf(req);
    }


    @Override
    @Transactional(readOnly = true)
    public String rsdcAuth(RsdcAuthReqVO req, String mbrsId) {
        // 1. 거주지 인증 API
        // processRsdcAuth(req);

        // 거주지 인증 rsp
        RsdcAuthRspVO rsdcAuthRspVO = new RsdcAuthRspVO(
                "address",
                "2018",
            "",
                "김"
        );

        // 행정동 코드 조회 req
        AddoCdReqVO reqVO = new AddoCdReqVO(
                "행정동 코드",
                "법정동 코드"
        );

        // 2. 거주지 인증이력 저장
//        svcJoinMapper.insertRsdcAuth(req);



        // 3. 행정동코드관리를 통해 해당 주소지의 기관코드 불러오기 (리스트가 될 수도 있음)
//      String orgCd = svcJoinMapper.selectOrgCdByAddoCdAndStdoCd(req.getAddoCd(), req.getStdoCd());
        String orgCd = "ORG0002";
        if (orgCd == null) {
//            res.setMsg("해당 지역 정보가 존재하지 않습니다.");
//            return res;
        }
        // 동일기관 여부 확인
        boolean sameArea = req.getOrgCd().equals(orgCd);

        if (!sameArea) {
            return "현재 거주지는 해당 서비스를 가입하실 수 없습니다.";
        }


        // 4. 해당 서비스 정보 조회
        List<PrevSvcRspVO> svcRspVO = svcJoinMapper.readSvcInf(req.getTpwSvcId());

        // 5. 이전 서비스 내역 조회
        List<PrevSvcRspVO> prevSvcList = svcJoinMapper.readPrevSvcInf(mbrsId);

        // 6. 이전 내역의 기관코드와 다를 경우 해지하시겠습니까 모달 요청
        if (req.getOrgCd().equals(prevSvcList.get(0).getOrgCd())) {
            return "이전 가입한 서비스가 존재합니다. 이전 서비스를 해지하시겠습니까?";
        }
        // 6-1. 해당 서비스 유형의 지원중복여부가 N일 경우
        if ("N".equals(svcRspVO.get(0).getSprtDplcYn())) {
            return "이전 가입한 서비스가 존재합니다. 이전 서비스를 해지하시겠습니까?";
        }

        // 6-2. 이전 내역의 기관코드가 같지만 지원중복여부가 N일 경우
        boolean svcDupYn = true;
        for (PrevSvcRspVO prevSvcRspVO : prevSvcList) {
            if (prevSvcRspVO != null && "N".equals(prevSvcRspVO.getSprtDplcYn())) {
                // 같은 회원서비스 정보를 조회 할 시,
                svcDupYn = false;
            }
        }
        if (!svcDupYn) {
            return "이전 서비스와 중복 가입이 불가합니다. 이전 서비스를 해지하시겠습니까?";
        }

        // 7. 이전 내역이 없을 경우 서비스 유형 선택 화면 이동 + 기관코드가 같은데 현재 서비스유형과 이전 서비스유형이 지원중복여부가 Y일 경우
        return "거주지 인증 완료";
    }

    @Override
    @Transactional(readOnly = true)
    public void svcCncn(SvcCncnReqVO req, String mbrsId) {
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<SvcJoinRspVO> readSvcJoin(SvcJoinReqVO req) {
        return svcJoinMapper.readSvcJoin(req);
    }

    @Override
    @Transactional
    public void svcJoin(SvcJoinReqVO req, String mbrsId) {

    }
}
