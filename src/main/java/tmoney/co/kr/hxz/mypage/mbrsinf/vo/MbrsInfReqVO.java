package tmoney.co.kr.hxz.mypage.mbrsinf.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MbrsInfReqVO {
    /** 회원 ID */
    private String mbrsId;
    /** 회원명 */
    private String mbrsNm;
    /** 이메일 주소 */
    private String mailAddr;
    /** 회원 휴대폰 번호 */
    private String mbrsMbphNo;
    /** 회원 전화번호 */
    private String mbrsTelNo;
    /** 비밀번호 */
    private String pwd;
    /** 회원 상태 코드 */
    private String mbrsStaCd;
    /** 비밀번호 오류 횟수 */
    private Integer pwdErrNcnt;
    /** 디스플레이 등록 상속 번호 */
    private String dsprRgtInhrNo;
    /** 거래 번호 */
    private String vtrnNo;
    /** 투표 참여 유형 코드 */
    private String tpwJoinTypCd;
    /** 투표 참여 관련 값 */
    private String tpwJoinRctdVal;
    /** 탈퇴 일시 */
    private String scsnDtm;
    /** 본인 인증 CI 암호값 */
    private String prsnAuthCiEncVal;
    /** 성별 코드 */
    private String gndrCd;
    /** 생년월일 */
    private String mbrsBrdt;
    /** 회원 탈퇴 구분 코드 */
    private String mbrsScsnDvsCd;
    /** 알림 여부 */
    private String ntfcYn;
    /** 마케팅 활용 동의 여부 */
    private String mrkgUtlzAgrmYn;
    /** SMS 수신 동의 여부 */
    private String smsRcvAgrmYn;
    /** 메일 수신 동의 여부 */
    private String mailRcvAgrmYn;
    /** BizTalk 수신 동의 여부 */
    private String bztlRcvAgrmYn;
    /** 회원 가입일 */
    private String mbrsJoinDt;
}
