package tmoney.co.kr.hxz.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DomainExceptionCode {
    AUTH(1500, "정의되지 않은 에러입니다."),
    LOGIN_FAIL(AUTH.code + 1, "로그인 실패"),
    LOGIN_ID_INVALID(AUTH.code + 2, "아이디가 일치하지 않습니다."),
    KAKAO_ACCESS_TOKEN_INVALID_IN_SESSION(AUTH.code + 3, "접근 토큰이 없거나 유효하지 않습니다."),
    KAKAO_ACCESS_TOKEN_EXPIRED(AUTH.code + 4, "접근 토큰이 만료되었습니다."),
    LOGIN_ID_NOT_FOUND(AUTH.code + 5, "로그인 ID가 존재하지 않습니다."),
    PASSWORD_DUPLICATION(AUTH.code + 5, "이전 비밀번호랑 동일한 비밀번호로 변경할 수 없습니다."),
    PASSWORD_VALIDATION_ERROR(AUTH.code + 5, "%s"),
    LOGIN_NEEDED(AUTH.code + 5, "로그인이 필요합니다.."),
    LOGIN_TAKEOVER(AUTH.code + 5, "다른 기기에서 로그인 중입니다. 기존 세션을 종료하시겠습니까?");

    private final int code;
    private final String message;

    public DomainException newInstance(int code, String message) {
        return new DomainException(code, message);
    }

    public DomainException newInstance(Throwable ex) {
        return new DomainException(code, message, ex);
    }

    public DomainException newInstance(Object... args) {
        return new DomainException(code, String.format(message, args));
    }

    public DomainException newInstance(Throwable ex, Object... args) {
        return new DomainException(code, String.format(message, args), ex);
    }


}
