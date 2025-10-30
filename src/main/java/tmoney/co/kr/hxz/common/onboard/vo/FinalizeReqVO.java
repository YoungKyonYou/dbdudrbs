package tmoney.co.kr.hxz.common.onboard.vo;

import lombok.Data;

import javax.validation.Valid;
import java.util.Map;

@Data
public class FinalizeReqVO {
    @Valid
    private Step1VO step1;
    @Valid private Step2VO step2;
    @Valid private Step3VO step3;
    @Valid private Step4VO step4;
    private String rcpt1;
    private String rcpt2;
    private String rcpt3;
    private String rcpt4;
}
