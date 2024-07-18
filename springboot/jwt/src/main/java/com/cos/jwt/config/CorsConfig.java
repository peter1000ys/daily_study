package com.cos.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있는지 설정하는 것
        config.addAllowedOrigin("*"); // e.g. http://domain1.com  모든 ip에 응답을 허용하겠다.
        config.addAllowedHeader("*");   // 모든 header에게 응답을 허용하겠다.
        config.addAllowedMethod("*"); // 모든 POST, GET, PUT, DELETE, PATCH 요청 허용

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }

}