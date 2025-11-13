package tmoney.co.kr.hxz.mypage.mypage.service;

import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvReqVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyLcgvRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.MyPageRspVO;
import tmoney.co.kr.hxz.mypage.mypage.vo.apl.MyAplRspVO;

import java.util.List;

public interface MyPageService {
    MyPageRspVO readMyPage(MyLcgvReqVO req, String mbrsId);
    MyAplRspVO readMyApl(MyLcgvReqVO req, String mbrsId);
    List<MyLcgvRspVO> readMyLcgv(MyLcgvReqVO req, String mbrsId);
    String readMyOrgNm(MyLcgvReqVO req, String mbrsId);

}
