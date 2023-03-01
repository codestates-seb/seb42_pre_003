package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.global.security.auth.dto.LogInMemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
public class MemberServiceStub implements MemberService {

    private final MemberMapper mapper;

    @Override
    public Member createMember(Member member) {
        return null;
    }

    @Override
    public Member createMemberByOauth2(Member member) {
        return null;
    }

    @Override
    public Member updateMember(Member memeber) {
        return null;
    }

    @Override
    public Member getMember(Long memberId) {
        return Member.builder()
            .memberId(memberId)
            .email("hgd@gmail.com")
            .name("홍길동")
            .state(MemberState.ACTIVE)
            .build();
    }

    @Override
    public Member getMember(String email) {
        return null;
    }

    @Override
    public void deleteMember(Long memberId) {

    }

    @Override
    public void verifyExistEmail(String email) {

    }

    @Override
    public Member findExistMemberById(Long memberId) {
        return null;
    }

    @Override
    public Member findExistMemberByEmail(String email) {
        return null;
    }

    @Override
    public Boolean verifyResourceOwner(Long memberId, LogInMemberDto LoginMember) {
        return false;
    }
}
