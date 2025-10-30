package tmoney.co.kr.hxz.etc.mbrsjoin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;
import tmoney.co.kr.hxz.etc.mbrsjoin.mapper.MbrsJoinMapper;
import tmoney.co.kr.hxz.etc.mbrsjoin.service.MbrsJoinService;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinReqVO;

@RequiredArgsConstructor
@Service
public class MbrsJoinServiceImpl implements MbrsJoinService {
    private final MbrsJoinMapper mbrsJoinMapper;

    @Override
    @Transactional
    public void insertMbrsJoin(MbrsJoinInstReqVO req) {
        // 아이디 중복 체크
        if (readMbrsCountById(req.getMbrsId())) {
            throw DomainExceptionCode.SIGNUP_ID_DUPLICATION_ERROR.newInstance("이미 사용중인 아이디입니다.");
        }

        // 비밀번호 확인 일치 검증
        if (!req.getPwd().equals(req.getCfmPwd())) {
            throw DomainExceptionCode.PASSWORD_VALIDATION_ERROR.newInstance("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 아이디 포함 검증
        if (req.getPwd().toLowerCase().contains(req.getMbrsId().toLowerCase())) {
            throw DomainExceptionCode.SIGNUP_PASSWORD_CONTAINS_ID.newInstance();
        }

        // 비밀번호 암호화

        MbrsJoinReqVO reqVO = new MbrsJoinReqVO(
                req.getMbrsId(),
                req.getMbrsNm(),
                req.getMailAddr(),
                req.getMbrsMbphNo(),
                req.getMbrsTelNo(),
                req.getPwd(),
                "00",
                0,
                null,
                null,
                "01",
                null,
                null,
                req.getPrsnAuthCiEncVal(),
                req.getGndrCd(),
                req.getMbrsBrdt(),
                null,
                "N",
                req.getMrkgUtlzAgrmYn(),
                req.getSmsRcvAgrmYn(),
                req.getMailRcvAgrmYn(),
                "Y"
        );
        mbrsJoinMapper.insertMbrsJoin(reqVO);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean readMbrsCountById(String checkId) {
        return mbrsJoinMapper.readMbrsCountById(checkId) > 0;
    }
}
