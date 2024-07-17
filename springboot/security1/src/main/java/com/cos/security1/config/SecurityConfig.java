package com.cos.security1.config;

import com.cos.security1.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) // Secured 어노테이션 활성화
// preAuthorize 어노테이션 활성화, postAuthorize 어노테이션 활성화
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

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
            request.requestMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MANAGER')");
            request.requestMatchers("/admin/**").access("hasRole('ADMIN')");
            request.requestMatchers("/").permitAll();
                });
        http.formLogin(form -> {
            form.loginPage("/loginForm");
            form.loginProcessingUrl("/login");   // 로그인 주소가 호출이 되면 시큐리티가 낚아 채서 대신 로그인을 진행
            form.defaultSuccessUrl("/");
        });
        http.oauth2Login(oauth -> {
            oauth.loginPage("/loginForm"); // 구글 로그인 완료하고 후처리가 필요
            // 코드 받기 = 인증 완료, 엑세스 토큰 = 권한이 생김, 사용자 프로필 정보 가져오고 그걸 토대로 회원가입 진행
            oauth.userInfoEndpoint(userInfo -> {
                userInfo.userService(principalOauth2UserService);
            });
        });


        return http.build();
    }

}
