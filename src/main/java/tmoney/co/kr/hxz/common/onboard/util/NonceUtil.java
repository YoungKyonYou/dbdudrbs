package tmoney.co.kr.hxz.common.onboard.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class NonceUtil {
    private final byte[] hmacKey;
    public NonceUtil(@Value("${app.onboarding.hmac-secret}") String base64Key) {
        this.hmacKey = Base64.getDecoder().decode(base64Key);
        if (this.hmacKey.length < 32) throw new IllegalArgumentException("HMAC secret must be >= 32 bytes (base64-decoded)");
    }
    public String issue(String onb, int step, String jti) {
        byte[] material = buildMaterial(onb, step, jti);
        return hmacBase64(material);
    }
    public boolean verify(String nonce, String onb, int step, String jti) {
        if (nonce == null)
            return false;
        byte[] material = buildMaterial(onb, step, jti);
        String expect = hmacBase64(material);
        return constantTimeEquals(expect, nonce);
    }
    private byte[] buildMaterial(String onb, int step, String jti) {
        try {
            byte version = 0x01;
            byte[] onbBytes = onb.getBytes(StandardCharsets.UTF_8);
            byte[] jtiBytes = jti.getBytes(StandardCharsets.UTF_8);
            byte[] onbLen = u16be(onbBytes.length);
            byte[] jtiLen = u16be(jtiBytes.length);
            byte[] step4 = u32be(step);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1 + 2 + onbBytes.length + 4 + 2 + jtiBytes.length);
            bos.write(version);
            bos.write(onbLen);  bos.write(onbBytes);
            bos.write(step4);
            bos.write(jtiLen);  bos.write(jtiBytes);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("nonce material build error", e);
        }
    }
    private static byte[] u16be(int n) {
        if (n < 0 || n > 0xFFFF)
            throw new IllegalArgumentException("u16 out of range");
        return new byte[] { (byte)((n >>> 8) & 0xFF), (byte)(n & 0xFF)
        };
    }
    private static byte[] u32be(int n) {
        return new byte[] {
            (byte)((n >>> 24) & 0xFF),
            (byte)((n >>> 16) & 0xFF),
            (byte)((n >>>  8) & 0xFF),
            (byte)( n         & 0xFF)
        };
    }
    private String hmacBase64(byte[] data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(hmacKey, "HmacSHA256"));
            byte[] out = mac.doFinal(data);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(out);
        } catch (Exception e) {
            throw new IllegalStateException("HMAC error", e);
        }
    }
    private boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) return false;
        byte[] aa = a.getBytes(StandardCharsets.US_ASCII);
        byte[] bb = b.getBytes(StandardCharsets.US_ASCII);
        if (aa.length != bb.length) return false;
        int diff = 0;
        for (int i = 0; i < aa.length; i++) diff |= (aa[i] ^ bb[i]);
        return diff == 0;
    }
}
