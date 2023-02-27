package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import org.springframework.stereotype.Service;

//@Service
public class MemberServiceStub implements MemberService {

    @Override
    public Member createMember(MemberDto.Post post) {
        return null;
    }

    @Override
    public Member updateMember(MemberDto.Patch patch, long memberId) {
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
    public void deleteMember(Long memberId) {

    }

    @Override
    public void verifyExistEmail(String email) {

    }

    @Override
    public Member findExistMemberById(Long memberId) {
        return null;
    }
}
