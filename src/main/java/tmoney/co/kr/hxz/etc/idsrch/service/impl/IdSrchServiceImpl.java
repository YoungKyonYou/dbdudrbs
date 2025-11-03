package tmoney.co.kr.hxz.etc.idsrch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tmoney.co.kr.hxz.error.exception.DomainExceptionCode;
import tmoney.co.kr.hxz.etc.idsrch.mapper.IdSrchMapper;
import tmoney.co.kr.hxz.etc.idsrch.service.IdSrchService;
import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchReqVO;
import tmoney.co.kr.hxz.etc.idsrch.vo.IdSrchRspVO;

@RequiredArgsConstructor
@Service
public class IdSrchServiceImpl implements IdSrchService {
    private final IdSrchMapper idSrchMapper;

    @Override
    @Transactional(readOnly = true)
    public IdSrchRspVO findMbrsId(IdSrchReqVO req) {
        IdSrchRspVO res = idSrchMapper.findMbrsId(req);

        if (res == null) {
            throw DomainExceptionCode.LOGIN_ID_NOT_FOUND.newInstance();
        }
        return idSrchMapper.findMbrsId(req);
    }
}
