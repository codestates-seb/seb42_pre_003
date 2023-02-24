package com.jmc.stackoverflowbe.auth.oauth.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.jmc.stackoverflowbe.auth.oauth.filter.JwtVerificationFilter;
import com.jmc.stackoverflowbe.auth.oauth.handler.MemberAccessDeniedHandler;
import com.jmc.stackoverflowbe.auth.oauth.handler.MemberAuthenticationEntryPoint;
import com.jmc.stackoverflowbe.auth.oauth.handler.OAuth2MemberSuccessHandler;
import com.jmc.stackoverflowbe.auth.oauth.jwt.JwtTokenizer;
import com.jmc.stackoverflowbe.auth.oauth.utils.CustomAuthorityUtils;
import com.jmc.stackoverflowbe.member.service.MemberService;
import java.util.Arrays;
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

@RequiredArgsConstructor
@Configuration
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
            .exceptionHandling()
            .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
            .accessDeniedHandler(new MemberAccessDeniedHandler())
            .and()
            .apply(new CustomFilterConfigurer())
            .and()
            .authorizeHttpRequests(authorize -> authorize
                .antMatchers(HttpMethod.GET, "/")
                .permitAll()
//                .antMatchers(HttpMethod.POST, "/*/members")
//                .permitAll()
//                .antMatchers(HttpMethod.PATCH, "/*/members/**")
//                .hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/*/members/**")
//                .hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.DELETE, "/*/members/**")
//                .hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/*/questions").hasRole("USER")
//                .antMatchers(HttpMethod.PATCH, "/*/questions/**").hasAnyRole("USER")
//                .antMatchers(HttpMethod.GET, "/*/questions/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/*/questions/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(
                    new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService))
                .loginPage("/")
            );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomFilterConfigurer extends
        AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter =
                new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
        }
    }
}
