package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.member.entity.Member;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {

    Member createMember(Member member);

    Member updateMember(Member member);

    Member getMember(Long id);

    void deleteMember(Long id);

    void verifyExistName(String Name);

    Member findExistId(Long id);
}
