package tmoney.co.kr.hxz.main.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.main.mapper.MainMapper;
import tmoney.co.kr.hxz.main.service.MainService;
import tmoney.co.kr.hxz.main.vo.MainNtcRspVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainServiceImpl implements MainService {
    private final MainMapper mainMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MainNtcRspVO> readMainNtcList() {
        return mainMapper.readMainNtcList();
    };
}
