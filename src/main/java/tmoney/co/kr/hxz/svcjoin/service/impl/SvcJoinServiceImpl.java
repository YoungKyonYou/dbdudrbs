package tmoney.co.kr.hxz.svcjoin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tmoney.co.kr.hxz.common.file.service.FileStorageService;
import tmoney.co.kr.hxz.common.util.AgeUtil;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;
import tmoney.co.kr.hxz.svcjoin.mapper.SvcJoinMapper;
import tmoney.co.kr.hxz.svcjoin.service.SvcJoinService;
import tmoney.co.kr.hxz.svcjoin.vo.prevsvc.PrevSvcRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.CmnRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.rsdc.RsdcAuthRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.svccncn.SvcCncnReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.*;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfReqVO;
import tmoney.co.kr.hxz.svcjoin.vo.orginf.OrgInfRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SvcJoinServiceImpl implements SvcJoinService {
    private final SvcJoinMapper svcJoinMapper;
    private final FileStorageService storageService;

    @Override
    @Transactional(readOnly = true)
    public OrgInfRspVO readOrgInf(OrgInfReqVO req) {
        return svcJoinMapper.readOrgInf(req);
    }


    @Override
    @Transactional(readOnly = true)
    public CmnRspVO<RsdcAuthRspVO> rsdcAuth(RsdcAuthReqVO req, String mbrsId) {
        try {
            // 주민번호 뒷자리 검사
            char genderCode = req.getKrn().charAt(7);
            switch (genderCode) {
                case '1': case '2': case '5': case '6':
                    break;
                case '3': case '4': case '7': case '8':
                    break;
                default:
                    throw DomainExceptionCode.RSDC_AUTH_ERROR.newInstance("잘못된 주민번호 형식입니다.");
            }
            // 1. 거주지 인증 API
            // processRsdcAuth(req);
//            RsdcCfmVO(addoCd,orgCd, svcRst, declrDate);
//            KrnCnfYnVO(addr, mvinDt, svcRst, rspNm);
            // 거주지 인증 rsp
            RsdcAuthRspVO rsdcAuthRspVO = new RsdcAuthRspVO(
                    "1101011000",
                    "11021",
                    "20180912"
            );

            // 2. 거주지 인증이력 저장
//            svcJoinMapper.insertRsdcAuth(req);


            // 3. 행정동코드관리를 통해 해당 주소지의 기관코드 불러오기 (리스트가 될 수도 있음)
            String orgCd = readOrgCdByAddoCd(rsdcAuthRspVO.getAddoCd());
            if (orgCd == null) {
                throw DomainExceptionCode.RSDC_AUTH_ERROR.newInstance("현재 거주지는 해당 서비스를 가입하실 수 없습니다.");
            }
            // 동일기관 여부 확인
            boolean sameArea = req.getOrgCd().equals(orgCd);

            if (!sameArea) {
                throw DomainExceptionCode.RSDC_AUTH_ERROR.newInstance("현재 거주지는 해당 서비스를 가입하실 수 없습니다.");
            }

            // 4. 해당 서비스 정보 조회
            List<PrevSvcRspVO> svcRspVO = readSvcInf(req.getTpwSvcId());

            // 5. 이전 서비스 내역 조회
            List<PrevSvcRspVO> prevSvcList = readPrevSvcInf(mbrsId);

            // 5-1. 이전 내역이 없을 경우 서비스 유형 선택 화면 이동
            if (prevSvcList.isEmpty()) {
                return new CmnRspVO<>(true, "거주지 인증 완료", rsdcAuthRspVO, null);
            }

            // 6. 이전 내역의 기관코드와 다를 경우 해지하시겠습니까 모달 요청
            if (req.getOrgCd().equals(prevSvcList.get(0).getOrgCd())) {
                return new CmnRspVO<>(false, "이전 가입한 서비스가 존재합니다. 이전 서비스를 해지하시겠습니까?", rsdcAuthRspVO, prevSvcList.get(0).getTpwSvcId());
            }
            // 6-1. 해당 서비스 유형의 지원중복여부가 N일 경우
            if ("N".equals(svcRspVO.get(0).getSprtDplcYn())) {
                return new CmnRspVO<>(false, "이전 가입한 서비스가 존재합니다. 이전 서비스를 해지하시겠습니까?", rsdcAuthRspVO, prevSvcList.get(0).getTpwSvcId());
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
                return new CmnRspVO<>(false, "이전 서비스와 중복 가입이 불가합니다. 이전 서비스를 해지하시겠습니까?", rsdcAuthRspVO, prevSvcList.get(0).getTpwSvcId());
            }
        } catch (Exception e) {
            throw DomainExceptionCode.RSDC_AUTH_ERROR.newInstance(e, "거주지 인증에 실패하였습니다. 다시 한번 시도해주십시오");
        }

        // 거주지 인증 rsp
        RsdcAuthRspVO rsdcAuth = new RsdcAuthRspVO(
                "1101011000",
                "11021",
                "20180912"
        );

        // 7. 기관코드가 같은데 현재 서비스유형과 이전 서비스유형이 지원중복여부가 Y일 경우 서비스 유형 선택 화면 이동
        return new CmnRspVO<>(true, "거주지 인증 완료", rsdcAuth, null);
    }

    @Transactional(readOnly = true)
    public String readOrgCdByAddoCd(String addoCd) {
        return svcJoinMapper.readOrgCdByAddoCd(addoCd);
    }

    @Transactional(readOnly = true)
    List<PrevSvcRspVO> readSvcInf(String tpwSvcId) {
        return svcJoinMapper.readSvcInf(tpwSvcId);
    }

    @Transactional(readOnly = true)
    List<PrevSvcRspVO> readPrevSvcInf(String mbrsId) {
        return svcJoinMapper.readPrevSvcInf(mbrsId);
    }

    @Override
    @Transactional(readOnly = true)
    public void svcPrevCncn(SvcCncnReqVO req, String mbrsId) {

    }

    @Override
    @Transactional(readOnly = true)
    public List<SvcJoinRspVO> readSvcJoin(SvcJoinReqVO req) {
        return svcJoinMapper.readSvcJoin(req);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankCdRspVO> readCmnBankCdList() {
        return svcJoinMapper.readCmnBankCdList();
    };

    @Override
    @Transactional
    public void svcJoin(RsdcInfReqVO rsdcInfReqVO, SvcJoinInstReqVO req, MultipartFile file, String mbrsId) {
        try {
            SvcTypInfReqVO svcTypInfReqVO = new SvcTypInfReqVO(req.getTpwSvcId(), req.getTpwSvcTypId());
            SvcTypInfRspVO rsp = readSvcTypInf(svcTypInfReqVO);

            if ("기타".equals(rsp.getMbrsClsNm()) && file == null) {
                throw DomainExceptionCode.SVC_TYP_ERROR.newInstance("첨부 파일이 필요한 유형입니다.");
            }

            // fileSaveMapper로 받은 첨부파일관리번호
            Long atflMngNo = 123123L;
            req.setAtflMngNo(atflMngNo);

            if("일반".equals(rsp.getMbrsClsNm()) && !AgeUtil.isValidAge(req.getKrn(), rsp.getTypAdptMinVal(), rsp.getTypAdptMaxVal())) {
                throw DomainExceptionCode.SVC_TYP_ERROR.newInstance("현재 서비스 유형에 유효하지 않은 나이입니다.");
            }
            insertSvcJoin(rsdcInfReqVO, req, mbrsId);
        } catch (Exception e) {
            throw DomainExceptionCode.SVC_TYP_ERROR.newInstance(e, "서비스 가입에 실패하였습니다. 다시 한번 시도해주십시오");
        }
    }

    @Transactional
    public SvcTypInfRspVO readSvcTypInf(SvcTypInfReqVO req) {
        return svcJoinMapper.readSvcTypInf(req);
    }

    @Transactional
    public void insertSvcJoin(RsdcInfReqVO rsdcInfReqVO, SvcJoinInstReqVO req, String mbrsId) {
        svcJoinMapper.insertSvcJoin(rsdcInfReqVO, req, mbrsId);
    }
}
