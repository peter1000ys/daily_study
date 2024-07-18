package com.cos.jwt.config;

import com.cos.jwt.filter.MyFilter1;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        // 세션을 쓰지 않겠다!
        http.sessionManagement(session ->{
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                });
        http.addFilterBefore(new MyFilter1(), SecurityContextHolderFilter.class);
        // 서버는 CORS 정책에서 벗어날 수 있다.
        http.addFilter(corsFilter);
        // formLogin 사용하지 않는다
        http.formLogin(formLogin -> formLogin.disable());
        http.httpBasic(httpBasic -> httpBasic.disable());
        http.authorizeRequests( request -> {
           request.requestMatchers("/api/v1/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')");
            request.requestMatchers("/api/v1/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')");
            request.requestMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')");
            request.requestMatchers("/").permitAll();
        });

        return http.build();
    }
}
