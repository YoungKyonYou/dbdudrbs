package tmoney.co.kr.hxz.news.faq.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.faq.mapper.FaqMapper;
import tmoney.co.kr.hxz.news.faq.service.FaqService;
import tmoney.co.kr.hxz.news.faq.vo.FaqRspVO;
import tmoney.co.kr.hxz.news.faq.vo.FaqSrchReqVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService {
    private final FaqMapper faqMapper;

    @Override
    @Transactional(readOnly = true)
    public PageDataVO<FaqRspVO> readFaqPaging(FaqSrchReqVO req) {
        final int offset = req.getPage() * req.getSize();

        long total = readFaqListCnt(req);

        FaqSrchReqVO reqVO = new FaqSrchReqVO(req.getFaqTtlNm(), req.getUseYn(), offset, req.getSize(), req.getSort(), req.getDir());

        List<FaqRspVO> content = readFaqList(reqVO);

        return new PageDataVO<>(content, req.getPage(), req.getSize(), total);
    };

    @Override
    @Transactional(readOnly = true)
    public List<FaqRspVO> readFaqList(FaqSrchReqVO req) {
        return faqMapper.readFaqList(req);
    };

    @Override
    @Transactional(readOnly = true)
    public long readFaqListCnt(FaqSrchReqVO req) {
        return faqMapper.readFaqListCnt(req);
    };

    @Override
    @Transactional(readOnly = true)
    public FaqRspVO readFaqDtl(String bltnNo) {
        return faqMapper.readFaqDtl(bltnNo);
    }

    @Override
    @Transactional
    public void updateFaqInqrNcnt(String bltnNo) {
        faqMapper.updateFaqInqrNcnt(bltnNo);
    }
}
