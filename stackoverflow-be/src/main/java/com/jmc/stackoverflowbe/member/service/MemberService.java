package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    Member createMember(Member member);

    Member updateMember(Member memeber);

    Member getMember(Long memberId);

    void deleteMember(Long memberId);

    void verifyExistEmail(String email);

    Member findExistMemberById(Long memberId);

    Member findExistMemberByEmail(String email);
}
