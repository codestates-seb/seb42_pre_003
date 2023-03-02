package com.jmc.stackoverflowbe.global.security.auth.config;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://http://seb42-pre-003.s3-website.ap-northeast-2.amazonaws.com/"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
//        config.addAllowedOriginPattern(Arrays.asList("http://localhost:3000", "http://seb42-pre-003.s3-website.ap-northeast-2.amazonaws.com/"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("*"));
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}