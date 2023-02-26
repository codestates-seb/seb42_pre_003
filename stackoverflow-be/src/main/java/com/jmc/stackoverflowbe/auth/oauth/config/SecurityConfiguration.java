package com.jmc.stackoverflowbe.auth.oauth.config;

import com.jmc.stackoverflowbe.auth.oauth.filter.JwtVerificationFilter;
import com.jmc.stackoverflowbe.auth.oauth.handler.MemberAccessDeniedHandler;
import com.jmc.stackoverflowbe.auth.oauth.handler.MemberAuthenticationEntryPoint;
import com.jmc.stackoverflowbe.auth.oauth.handler.OAuth2MemberSuccessHandler;
import com.jmc.stackoverflowbe.auth.oauth.jwt.JwtTokenizer;
import com.jmc.stackoverflowbe.auth.oauth.utils.CustomAuthorityUtils;
import com.jmc.stackoverflowbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling() // 추가
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint()) // 추가
                .accessDeniedHandler(new MemberAccessDeniedHandler()) // 추가
                .and()
                .apply(new CustomFilterConfigurer()) // 추가
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService)) // (1)
                );

        return http.build();
    }

    // 추가
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
        }
    }
}