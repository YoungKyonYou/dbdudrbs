package tmoney.co.kr.hxz.mypage.mbrsinf.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;
import tmoney.co.kr.hxz.mypage.mbrsinf.mapper.MbrsInfMapper;
import tmoney.co.kr.hxz.mypage.mbrsinf.service.MbrsInfService;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfReqVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsInfRspVO;
import tmoney.co.kr.hxz.mypage.mbrsinf.vo.MbrsUpdReqVO;

@RequiredArgsConstructor
@Service
public class MbrsInfServiceImpl implements MbrsInfService {
    private final MbrsInfMapper mbrsInfMapper;

    @Override
    @Transactional(readOnly = true)
    public MbrsInfRspVO readMbrsInf(String mbrsId) {
        return mbrsInfMapper.readMbrsInf(mbrsId);
    }

    @Override
    @Transactional
    public void updateMbrsInf(MbrsUpdReqVO req) {
        mbrsInfMapper.updateMbrsInf(req);

        MbrsInfRspVO rspVO = readMbrsInf(req.getMbrsId());
        MbrsInfReqVO reqVO = new MbrsInfReqVO(
                req.getMbrsId(),
                req.getMbrsNm(),
                req.getMailAddr(),
                req.getMbrsMbphNo(),
                req.getMbrsTelNo(),
                rspVO.getPwd(),
                req.getMbrsStaCd(),
                rspVO.getPwdErrNcnt(),
                rspVO.getDsprRgtInhrNo(),
                rspVO.getVtrnNo(),
                rspVO.getTpwJoinTypCd(),
                rspVO.getTpwJoinRctdVal(),
                rspVO.getScsnDtm(),
                req.getPrsnAuthCiEncVal(),
                req.getGndrCd(),
                req.getMbrsBrdt(),
                rspVO.getMbrsScsnDvsCd(),
                rspVO.getNtfcYn(),
                rspVO.getMrkgUtlzAgrmYn(),
                rspVO.getSmsRcvAgrmYn(),
                rspVO.getMailRcvAgrmYn(),
                rspVO.getBztlRcvAgrmYn(),
                rspVO.getMbrsJoinDt()
        );
        insertMbrsInf(reqVO);
    }

    @Override
    @Transactional
    public void insertMbrsInf(MbrsInfReqVO req) {
        mbrsInfMapper.insertMbrsInf(req);
    }



    @Override
    @Transactional
    public void updatePwd(String mbrsId, String newPwd, String cfmPwd) {
        // 세션에 로그인 ID 저장돼 있다고 가정
        if (mbrsId == null) {
            throw DomainExceptionCode.LOGIN_ID_NOT_FOUND.newInstance();
        }
        if (!newPwd.equals(cfmPwd)) {
            throw DomainExceptionCode.PASSWORD_VALIDATION_ERROR.newInstance("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 형식 검증 (영문, 숫자, 특수문자 포함 8~20자)
        String pwdRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,20}$";
        if (!newPwd.matches(pwdRegex)) {
            throw DomainExceptionCode.PASSWORD_VALIDATION_ERROR.newInstance("비밀번호는 영문, 숫자, 특수문자 중 두 두가지 포함 8~20자입니다.");
        }

        mbrsInfMapper.updatePwd(mbrsId, newPwd);
    }
}
