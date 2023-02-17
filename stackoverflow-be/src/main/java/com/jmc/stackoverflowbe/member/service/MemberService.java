package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember() {
        return null;
    }

    public Member updateMember() {
        return null;
    }

    public Member getMember() {
        return null;
    }

    public void deleteMember() {

    }
}
