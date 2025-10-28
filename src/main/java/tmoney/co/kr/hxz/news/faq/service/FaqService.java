package tmoney.co.kr.hxz.news.faq.service;

import tmoney.co.kr.hxz.common.page.vo.PageDataVO;
import tmoney.co.kr.hxz.news.faq.vo.FaqRspVO;
import tmoney.co.kr.hxz.news.faq.vo.FaqSrchReqVO;

import java.util.List;

public interface FaqService {
    PageDataVO<FaqRspVO> readFaqPaging(FaqSrchReqVO req);

    List<FaqRspVO> readFaqList(FaqSrchReqVO req);

    long readFaqListCnt(FaqSrchReqVO req);

    FaqRspVO readFaqDtl(String bltnNo);

    void updateFaqInqrNcnt(String bltnNo);
}
