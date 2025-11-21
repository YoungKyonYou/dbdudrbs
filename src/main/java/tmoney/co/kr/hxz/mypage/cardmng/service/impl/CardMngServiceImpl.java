package tmoney.co.kr.hxz.mypage.cardmng.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.common.PageData;
import tmoney.co.kr.hxz.mypage.acntmng.service.AcntMngService;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngReqVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.AcntMngRspVO;
import tmoney.co.kr.hxz.mypage.acntmng.vo.TpwMbrsSvcVO;
import tmoney.co.kr.hxz.mypage.cardmng.mapper.CardMngMapper;
import tmoney.co.kr.hxz.mypage.cardmng.service.CardMngService;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngInstReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngReqVO;
import tmoney.co.kr.hxz.mypage.cardmng.vo.CardMngRspVO;
import tmoney.co.kr.hxz.svcjoin.vo.svcjoin.BankCdRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardMngServiceImpl implements CardMngService {
    private final CardMngMapper cardMngMapper;
    private final AcntMngService acntMngService;

    @Override
    @Transactional(readOnly = true)
    public PageData<CardMngRspVO> readCardMngPaging(CardMngReqVO req, String mbrsId) {
        final int offset = req.getPage() * req.getSize();

        long total = readCardMngCnt(req, mbrsId);

        CardMngReqVO reqVO = new CardMngReqVO(req.getTpwSvcId(), offset, req.getSize(), req.getSort(), req.getDir());
        List<CardMngRspVO> content = readCardMng(reqVO, mbrsId);

        return new PageData<>(content, req.getPage(), req.getSize(), total);
    }

    @Override
    @Transactional(readOnly = true)
    public CardMngRspVO readPrsCardMng(CardMngReqVO req, String mbrsId) {
        return readCardMng(new CardMngReqVO(req.getTpwSvcId(), 0, 10, req.getSort(), req.getDir()), mbrsId).get(0);
    }

    @Transactional(readOnly = true)
    public List<CardMngRspVO> readCardMng(CardMngReqVO req, String mbrsId) {
        return cardMngMapper.readCardMng(req, mbrsId);
    }

    @Transactional(readOnly = true)
    public long readCardMngCnt(CardMngReqVO req, String mbrsId) {
        return cardMngMapper.readCardMngCnt(req, mbrsId);
    }

    @Override
    @Transactional
    public void cardMng(CardMngInstReqVO req, String mbrsId) {
        // 가입된 회원 서비스 목록 조회
        List<TpwMbrsSvcVO> tpwMbrsSvcVO = acntMngService.mbrsSvcList("가입", mbrsId);

        for (TpwMbrsSvcVO vo : tpwMbrsSvcVO) {
            TpwMbrsSvcVO reqVO = new TpwMbrsSvcVO(
                    vo.getMbrsId(), vo.getMbrsSvcJoinDt(), vo.getTpwSvcTypId(), vo.getTpwSvcId(),
                    req.getCardNo(), vo.getBnkCd(), req.getCardNo(), vo.getOoaNm(), vo.getStdoCd(),
                    vo.getMvinDt(), vo.getMvotDt(), vo.getTpwMbrsSvcStaCd(), vo.getAtflMngNo(), vo.getMbrsSvcCncnDt()
            );

            // 회원 서비스 카드 이력 정보 변경
            acntMngService.updateMbrsSvc(reqVO);
            // 회원 서비스 변경 이력 등록
            acntMngService.insertMbrsSvcHist(reqVO);

            CardMngInstReqVO cardMngInstReqVO = new CardMngInstReqVO(
                    vo.getTpwSvcId(), vo.getTpwSvcTypId(),
                    req.getCardSttDt(), req.getCardEndDt(), req.getCardNo()
            );
            // 카드 변경 이력 추가
            insertCardMng(cardMngInstReqVO, mbrsId);
        }
    }

    @Transactional
    public void insertCardMng(CardMngInstReqVO req, String mbrsId) {
        cardMngMapper.insertCardMng(req, mbrsId);
    }
}
