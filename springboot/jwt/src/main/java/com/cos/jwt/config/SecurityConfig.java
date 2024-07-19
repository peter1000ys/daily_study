package com.cos.jwt.config;

import com.cos.jwt.config.jwt.JwtAuthenticationFilter;
import com.cos.jwt.config.jwt.JwtAuthorizationFilter;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();


        http.csrf(csrf -> csrf.disable());
        // 세션을 쓰지 않겠다!
        http.sessionManagement(session ->{
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                });
//        http.addFilterBefore(new MyFilter1(), SecurityContextHolderFilter.class);
        // 서버는 CORS 정책에서 벗어날 수 있다.
        http.addFilter(corsFilter);
        http.addFilter(new JwtAuthenticationFilter(authenticationManager)); // AuthenticationManager
        http.addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        // formLogin 사용하지 않는다
        http.formLogin(formLogin -> formLogin.disable());
        http.httpBasic(httpBasic -> httpBasic.disable());
        http.authorizeRequests( request -> {
           request.requestMatchers("/api/v1/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')");
            request.requestMatchers("/api/v1/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')");
            request.requestMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')");
            request.requestMatchers("/").permitAll();
        });
//        http.formLogin( formLogin ->
//                formLogin.loginProcessingUrl("/login"));

        return http.build();
    }


}
