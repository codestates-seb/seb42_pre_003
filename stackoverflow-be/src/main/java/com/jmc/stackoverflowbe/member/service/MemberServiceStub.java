package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Member updateMember(Member member) {
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
}
