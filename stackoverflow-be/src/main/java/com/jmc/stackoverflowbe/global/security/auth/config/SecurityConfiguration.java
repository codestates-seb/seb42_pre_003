package com.jmc.stackoverflowbe.global.security.auth.config;

import com.jmc.stackoverflowbe.global.security.auth.filter.JwtVerificationFilter;
import com.jmc.stackoverflowbe.global.security.auth.handler.Env;
import com.jmc.stackoverflowbe.global.security.auth.handler.MemberAccessDeniedHandler;
import com.jmc.stackoverflowbe.global.security.auth.handler.MemberAuthenticationEntryPoint;
import com.jmc.stackoverflowbe.global.security.auth.handler.OAuth2MemberSuccessHandler;
import com.jmc.stackoverflowbe.global.security.auth.jwt.JwtTokenizer;
import com.jmc.stackoverflowbe.global.security.auth.utils.CustomAuthorityUtils;
import com.jmc.stackoverflowbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    private final Env env;

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
            .exceptionHandling()  // 추가
            .authenticationEntryPoint(new MemberAuthenticationEntryPoint())  // 추가
            .accessDeniedHandler(new MemberAccessDeniedHandler())            // 추가
            .and()
            .apply(new CustomFilterConfigurer())  // 추가
            .and()
            .authorizeHttpRequests(authorize -> authorize // url authorization 전체 추가
                .antMatchers(HttpMethod.POST, "/members/**").permitAll()
                .antMatchers(HttpMethod.GET, "/members").permitAll()
                .antMatchers(HttpMethod.GET, "/members/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/members/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/members/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/questions/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/questions/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/questions/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/questions/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/tags/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/tags/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/tags/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/tags/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/answers/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/answers/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/answers/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.DELETE, "/answers/**").hasAnyRole("USER")
                .anyRequest().permitAll()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService, env))  // (1)
            );

        return http.build();
    }

    // 추가
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
        }
    }
}