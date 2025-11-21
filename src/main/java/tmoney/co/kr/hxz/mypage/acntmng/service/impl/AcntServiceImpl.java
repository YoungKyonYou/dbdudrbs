package tmoney.co.kr.hxz.mypage.acntmng.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.acntmng.vo.TpwMbrsSvcVO;
import tmoney.co.kr.hxz.mypage.acntmng.mapper.AcntMngMapper;
import tmoney.co.kr.hxz.mypage.acntmng.service.AcntMngService;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngInstReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngRspVO;
import tmoney.co.kr.hxz.svcjoin.service.SvcJoinService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AcntServiceImpl implements AcntMngService {
    private final AcntMngMapper acntMngMapper;
    private final SvcJoinService svcJoinService;

    @Override
    @Transactional(readOnly = true)
    public PageData<AcntMngRspVO> readAcntMngPaging(AcntMngReqVO req, String mbrsId) {
        final int offset = req.getPage() * req.getSize();

        long total = readAcntMngCnt(req, mbrsId);

        AcntMngReqVO reqVO = new AcntMngReqVO(req.getTpwSvcId(), offset, req.getSize(), req.getSort(), req.getDir());
        List<AcntMngRspVO> content = readAcntMng(reqVO, mbrsId);

        return new PageData<>(content, req.getPage(), req.getSize(), total);
    }

    @Override
    @Transactional(readOnly = true)
    public AcntMngRspVO readPrsAcntMng(AcntMngReqVO req, String mbrsId) {
        return readAcntMng(new AcntMngReqVO(req.getTpwSvcId(), 0, 10, req.getSort(), req.getDir()), mbrsId).get(0);
    }

    @Transactional(readOnly = true)
    public List<AcntMngRspVO> readAcntMng(AcntMngReqVO req, String mbrsId) {
        return acntMngMapper.readAcntMng(req, mbrsId);
    }

    @Transactional(readOnly = true)
    public long readAcntMngCnt(AcntMngReqVO req, String mbrsId) {
        return acntMngMapper.readAcntMngCnt(req, mbrsId);
    }

    @Override
    @Transactional
    public void acntMng(AcntMngInstReqVO req, String mbrsId) {
        // 가입된 회원 서비스 목록 조회
        List<TpwMbrsSvcVO> tpwMbrsSvcVO = mbrsSvcList("가입", mbrsId);


        for (TpwMbrsSvcVO vo : tpwMbrsSvcVO) {
            TpwMbrsSvcVO reqVO = new TpwMbrsSvcVO(
                    vo.getMbrsId(), vo.getMbrsSvcJoinDt(), vo.getTpwSvcTypId(), vo.getTpwSvcId(),
                    vo.getCardNo(), req.getBnkCd(), req.getAcntNo(), req.getOoaNm(), vo.getStdoCd(),
                    vo.getMvinDt(), vo.getMvotDt(), vo.getTpwMbrsSvcStaCd(), vo.getAtflMngNo(), vo.getMbrsSvcCncnDt()
            );

            // 회원 서비스 계좌 이력 정보 변경
            updateMbrsSvc(reqVO);
            // 회원 서비스 변경 이력 등록
            insertMbrsSvcHist(reqVO);

            AcntMngInstReqVO acntMngInstReqVO = new AcntMngInstReqVO(
                    vo.getTpwSvcId(), vo.getTpwSvcTypId(),
                    req.getBnkCd(), req.getAcntNo(), req.getOoaNm()
            );

            // 계좌 변경 이력 추가
            insertAcntMng(acntMngInstReqVO, mbrsId);
        }


    }

    @Transactional
    public void insertAcntMng(AcntMngInstReqVO req, String mbrsId) {
        acntMngMapper.insertAcntMng(req, mbrsId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TpwMbrsSvcVO> mbrsSvcList(String mbrsSvcSta, String mbrsId) {
        String tpwMbrsSvcStaCd;
        if (mbrsSvcSta == null) {
            tpwMbrsSvcStaCd = "not in ('98', '99')";
        } else if (mbrsSvcSta.equals("해지")) {
            tpwMbrsSvcStaCd = "in ('98', '99')";
        } else if (mbrsSvcSta.equals("가입")) {
            tpwMbrsSvcStaCd = "= '04'";
        } else if (mbrsSvcSta.equals("신청")) {
            tpwMbrsSvcStaCd = "= '01'";
        } else {
            tpwMbrsSvcStaCd = "not in ('98', '99')";
        };

        return readMbrsSvcList(tpwMbrsSvcStaCd, mbrsId);
    }

    @Transactional(readOnly = true)
    public List<TpwMbrsSvcVO> readMbrsSvcList(String tpwMbrsSvcStaCd, String mbrsId) {
        return acntMngMapper.readMbrsSvcList(tpwMbrsSvcStaCd, mbrsId);
    }

    @Override
    @Transactional
    public void updateMbrsSvc(TpwMbrsSvcVO req) {
        acntMngMapper.updateMbrsSvc(req);
    }

    @Override
    @Transactional
    public void insertMbrsSvcHist(TpwMbrsSvcVO req) {
        acntMngMapper.insertMbrsSvcHist(req);
    }
}
