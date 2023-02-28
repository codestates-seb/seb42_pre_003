package com.jmc.stackoverflowbe.global.security.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Oauth2MemberDto {
    private Long memberId;
    private String email;

    @Builder
    public Oauth2MemberDto(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }
}
