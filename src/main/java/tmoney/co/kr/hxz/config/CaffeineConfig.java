package tmoney.co.kr.hxz.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
@Configuration
public class CaffeineConfig {
    @Bean public Cache<String, Boolean> jtiCache() { return Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(15)).maximumSize(1_000_000).build(); }
    @Bean public Cache<String, Boolean> blocklistCache() { return Caffeine.newBuilder().expireAfterWrite(Duration.ofHours(2)).maximumSize(1_000_000).build(); }
}
