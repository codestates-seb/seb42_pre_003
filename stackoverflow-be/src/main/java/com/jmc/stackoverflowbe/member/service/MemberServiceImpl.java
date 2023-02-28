package com.jmc.stackoverflowbe.member.service;

import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
import com.jmc.stackoverflowbe.global.exception.ExceptionCode;
import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.mapper.MemberMapper;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(Member member) {
        // 동일한 이메일이 존재하는지 확인.
        verifyExistEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member createMemberByOauth2(Member member) {
        // 동일한 이메일이 존재하는지 확인.
        verifyExistEmail(member.getEmail());

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member member) {
        // 변경할 회원 정보가 존재하는지 검증.
        Member obtainedMember = findExistMemberById(member.getMemberId());

        // member의 값이 비어있지 않으면 obtainedMember의 값을 변경.
        Optional.ofNullable(member.getName())
                .ifPresent(obtainedMember::setName);
        Optional.ofNullable(member.getAbout())
                .ifPresent(obtainedMember::setAbout);
        Optional.ofNullable(member.getLocation())
                .ifPresent(obtainedMember::setLocation);

        return memberRepository.save(obtainedMember);
    }

    @Override
    public Member getMember(Long memberId) {

        return findExistMemberById(memberId);
    }

    @Override
    public Member getMember(String email) {

        return findExistMemberByEmail(email);
    }

    @Override
    public void deleteMember(Long memberId) {
        Member obtainedMember = findExistMemberById(memberId);

        // 탈퇴 상태로 변경.
        obtainedMember.setState(MemberState.DELETED);

        memberRepository.save(obtainedMember);
    }

    @Override
    public void verifyExistEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        // Optional Member에 값이 존재하다면 예외 발생.
        if (optionalMember.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
    }

    @Override
    public Member findExistMemberById(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // Optional Member에 값이 존재하지 않다면 예외 발생.
        Member obtainedMember = optionalMember
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        // 찾은 멤버가 탈퇴 상태라면 예외 발생.
        if (obtainedMember.getState() == MemberState.DELETED) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return obtainedMember;
    }

    @Override
    public Member findExistMemberByEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        // Optional Member에 값이 존재하지 않다면 예외 발생.
        Member obtainedMember = optionalMember
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        // 찾은 멤버가 탈퇴 상태라면 예외 발생.
        if (obtainedMember.getState() == MemberState.DELETED) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return obtainedMember;
    }
}
