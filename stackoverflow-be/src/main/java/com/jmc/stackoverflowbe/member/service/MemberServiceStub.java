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
    public Member createMember(MemberDto.Post post) {
        return null;
    }

    @Override
    public Member updateMember(MemberDto.Patch patch, long memberId) {
        return null;
    }

    @Override
    public MemberDto.Response getMember(Long memberId) {
        Member.builder()
            .memberId(memberId)
            .email("hgd@gmail.com")
            .name("홍길동")
            .state(MemberState.ACTIVE)
            .build();
        return mapper.memberToResponseDto(findExistMemberById(memberId));


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
