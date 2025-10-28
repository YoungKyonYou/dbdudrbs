package tmoney.co.kr.hxz.news.faq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tmoney.co.kr.hxz.annotation.HxzDb;
import tmoney.co.kr.hxz.news.faq.vo.FaqRspVO;
import tmoney.co.kr.hxz.news.faq.vo.FaqSrchReqVO;

import java.util.List;

@HxzDb
@Mapper
public interface FaqMapper {
    List<FaqRspVO> readFaqList(
            @Param("req") FaqSrchReqVO req
    );

    long readFaqListCnt(@Param("req") FaqSrchReqVO req);

    FaqRspVO readFaqDtl(@Param("bltnNo") String bltnNo);
}
