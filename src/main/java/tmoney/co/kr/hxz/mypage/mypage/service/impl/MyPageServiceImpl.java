package tmoney.co.kr.hxz.mypage.mypage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.mypage.mypage.mapper.MyPageMapper;
import tmoney.co.kr.hxz.mypage.mypage.service.MyPageService;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvReqVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyPageRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.apl.MyAplRspVO;

@RequiredArgsConstructor
@Service
public class MyPageServiceImpl implements MyPageService {
    private final MyPageMapper myPageMapper;

    @Override
    @Transactional(readOnly = true)
    public MyPageRspVO readMyPage(MyLcgvReqVO req, String mbrsId) {
        return myPageMapper.readMyPage(req, mbrsId);
    }

    @Override
    @Transactional(readOnly = true)
    public MyAplRspVO readMyApl(MyLcgvReqVO req, String mbrsId) {
        return myPageMapper.readMyApl(req, mbrsId);
    }

    @Override
    @Transactional(readOnly = true)
    public MyLcgvRspVO readMyLcgv(MyLcgvReqVO req, String mbrsId) {
        return myPageMapper.readMyLcgv(req, mbrsId);
    }
}
