package tmoney.co.kr.hxz.common.onboard.service;

import java.time.Duration;
import java.util.Map;

public interface ReceiptService {
    <T> String issueReceiptFromMap(String onb, int step, T payload, Duration ttl);
    String issueReceiptFromJson(String onb, int step, String payloadJson, Duration ttl);
    <T> void verifyAndConsumeFromMap(String receiptJws, String expectedOnb, int expectedStep, T payload);
    void verifyAndConsumeFromJson(String receiptJws, String expectedOnb, int expectedStep, String payloadJson);
}
