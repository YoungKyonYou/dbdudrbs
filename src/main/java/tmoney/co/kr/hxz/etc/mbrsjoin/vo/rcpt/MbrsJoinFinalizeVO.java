package tmoney.co.kr.hxz.etc.mbrsjoin.vo.rcpt;

import lombok.Data;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.MbrsJoinInstReqVO;
import tmoney.co.kr.hxz.etc.mbrsjoin.vo.PrsnAuthReqVO;

import javax.validation.Valid;

@Data
public class MbrsJoinFinalizeVO {
    @Valid
    private PrsnAuthReqVO step1;
    @Valid
    private MbrsJoinInstReqVO step2;
    private String rcpt1;
    private String rcpt2;
}
