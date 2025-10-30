package tmoney.co.kr.hxz.common.onboard.util;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;

/**
 * 영수증(payloadHash) 계산을 위한 JSON 정규화 & 해시 유틸.
 * - JSON은 항상 "키 정렬 + 공백 없는 compact" 형태로 직렬화
 * - SHA-256 Base64URL(= 패딩 없는 URL-safe)로 해시 인코딩
 */

@Component
public final class ReceiptUtil {

    private final ObjectMapper CANONICAL_MAPPER ;

    public ReceiptUtil(@Qualifier("canonicalObjectMapper") ObjectMapper CANONICAL_MAPPER) {
        this.CANONICAL_MAPPER = CANONICAL_MAPPER;
    }

    /** Map/DTO를 정규화된 JSON 문자열로 직렬화 */
    public String toCanonicalJson(Object any) {
        try {
            return CANONICAL_MAPPER.writeValueAsString(any);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to canonicalize JSON", e);
        }
    }

    /** 이미 JSON 문자열이라면 그대로 해시 가능 */
    public String sha256Base64UrlFromJson(String json) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dig = md.digest(json.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(dig);
        } catch (Exception e) {
            throw new IllegalStateException("Hash error", e);
        }
    }

    /** Map → canonical JSON → 해시 */
    public String sha256Base64UrlFromMap(Map<String,Object> payload) {
        return sha256Base64UrlFromJson(toCanonicalJson(payload));
    }
}
