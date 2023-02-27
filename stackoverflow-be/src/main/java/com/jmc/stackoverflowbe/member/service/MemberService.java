package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    Member createMember(MemberDto.Post post);

    Member updateMember(MemberDto.Patch patch, long memberId);

    MemberDto.Response getMember(Long memberId);

    void deleteMember(Long memberId);

    void verifyExistEmail(String email);

    Member findExistMemberById(Long memberId);
}
