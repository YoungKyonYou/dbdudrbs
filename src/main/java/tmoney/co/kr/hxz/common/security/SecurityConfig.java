package tmoney.co.kr.hxz.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // 새 DSL에서 쓸 AuthorizationManager
    private final AuthorizationManager<RequestAuthorizationContext> onboardingAuthz;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession() // JSESSIONID 재발급(권장)
                .and()

                .exceptionHandling()
                .authenticationEntryPoint((req, res, e) -> res.sendRedirect("/etc/mbrsjoin/start"))
                .accessDeniedHandler((req, res, e) -> res.sendRedirect("/etc/mbrsjoin/start"))
                .and()

                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/css/**", "/js/**", "/images/**", "/etc/mbrsjoin/start").permitAll()
                        .antMatchers("/etc/mbrsjoin", "/etc/mbrsjoin/**").access(onboardingAuthz) // 여기만 커스텀 권한
                        .anyRequest().permitAll() // 필요하면 authenticated()로 바꾸세요
                )

                .formLogin().disable()
                .logout().disable();

        return http.build();
    }
}
