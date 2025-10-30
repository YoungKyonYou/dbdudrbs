package tmoney.co.kr.hxz.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
@Configuration
public class KeyConfig {
    @Bean public KeyPair jwtKeyPair() {
        try { KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA"); gen.initialize(2048); return gen.generateKeyPair(); }
        catch (Exception e) { throw new IllegalStateException("RSA keypair generation failed", e); }
    }
    @Bean public RSASSASigner jwtSigner(KeyPair kp) { return new RSASSASigner(kp.getPrivate()); }
    @Bean public RSASSAVerifier jwtVerifier(KeyPair kp) { return new RSASSAVerifier((java.security.interfaces.RSAPublicKey) kp.getPublic()); }
    @Bean public JWSAlgorithm jwtAlg() { return JWSAlgorithm.RS256; }
}
