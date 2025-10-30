package tmoney.co.kr.hxz.common.onboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.index.qual.SearchIndexBottom;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrecheckContext {
    private String onb;
    private String jti;
    private int step;
    private int mask;

}
