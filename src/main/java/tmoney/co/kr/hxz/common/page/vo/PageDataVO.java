package tmoney.co.kr.hxz.common.page.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageDataVO<T> {
    private List<T> content;
    private int page;
    private int size;
    private long total;

    public long getTotalPage() { return (total + size - 1) / size; }
}
