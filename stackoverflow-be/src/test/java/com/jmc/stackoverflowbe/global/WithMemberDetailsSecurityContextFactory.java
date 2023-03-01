package com.jmc.stackoverflowbe.global;

import com.jmc.stackoverflowbe.global.security.auth.dto.LogInMemberDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMemberDetailsSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomMember> {
    @Override
    public org.springframework.security.core.context.SecurityContext createSecurityContext(
        WithMockCustomMember customUser) {
        org.springframework.security.core.context.SecurityContext context = SecurityContextHolder.createEmptyContext();

        LogInMemberDto principal = LogInMemberDto.builder()
            .memberId(customUser.first())
            .email(customUser.second())
            .build();
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getEmail(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        context.setAuthentication(auth);
        return context;
    }
}