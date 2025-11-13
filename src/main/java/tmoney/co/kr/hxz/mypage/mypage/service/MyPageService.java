package tmoney.co.kr.hxz.mypage.mypage.service;

import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvReqVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyPageRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.apl.MyAplRspVO;

public interface MyPageService {
    MyPageRspVO readMyPage(MyLcgvReqVO req, String mbrsId);
    MyLcgvRspVO readMyLcgv(MyLcgvReqVO req, String mbrsId);
    MyAplRspVO readMyApl(MyLcgvReqVO req, String mbrsId);
}
