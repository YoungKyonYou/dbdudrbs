package tmoney.co.kr.hxz.common.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeUtil {
    public static boolean isValidAge(String rrn, String minAgeStr, String maxAgeStr) {
        int age = getAge(rrn);
        int minAge = Integer.parseInt(minAgeStr);
        int maxAge = Integer.parseInt(maxAgeStr);

        return age >= minAge && age <= maxAge;
    }

    /**
     * 주민등록번호로 나이 계산
     *
     * @param rrn 주민등록번호 (예: "9901011234567")
     * @return 현재 만 나이
     */
    public static int getAge(String rrn) {
        if (rrn == null || (rrn.length() != 13 && rrn.length() != 14)) {
            throw new IllegalArgumentException("잘못된 주민등록번호 형식: " + rrn);
        }

        // 하이픈 제거
        rrn = rrn.replace("-", "");

        // 생년월일 및 성별 코드 추출
        String birth = rrn.substring(0, 6);  // YYMMDD
        char genderCode = rrn.charAt(6);

        // 출생 세기 판별
        String prefix;
        switch (genderCode) {
            case '1': case '2': case '5': case '6':
                prefix = "19"; // 1900년대
                break;
            case '3': case '4': case '7': case '8':
                prefix = "20"; // 2000년대
                break;
            default:
                throw new IllegalArgumentException("잘못된 성별/세기 코드: " + genderCode);
        }

        // 생년월일 → LocalDate 변환
        int year = Integer.parseInt(prefix + birth.substring(0, 2));
        int month = Integer.parseInt(birth.substring(2, 4));
        int day = Integer.parseInt(birth.substring(4, 6));
        LocalDate birthDate = LocalDate.of(year, month, day);

        // 현재 나이 계산
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
