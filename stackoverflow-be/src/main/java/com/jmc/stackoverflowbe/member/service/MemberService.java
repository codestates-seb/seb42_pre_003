package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface MemberService {

    Member createMember();

    Member updateMember();

    Member getMember();

    void deleteMember();
}
