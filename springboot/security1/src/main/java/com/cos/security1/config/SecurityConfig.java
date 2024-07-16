package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;

    // 해당 메서드의 리턴되는 오브젝트를 IOC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeRequests( request -> {
            request.requestMatchers("/user/**").authenticated();
            request.requestMatchers("/manger/**").access("hasRole('ADMIN') or hasRole('MANAGER')");
            request.requestMatchers("/admin/**").access("hasRole('ADMIN')");
            request.requestMatchers("/").permitAll();
                });
        http.formLogin(form -> {
            form.loginPage("/loginForm");
            form.loginProcessingUrl("/login");        // 로그인 주소가 호출이 되면 시큐리티가 낚아 채서 대신 로그인을 진행
            form.defaultSuccessUrl("/");
        });


        return http.build();
    }

}
