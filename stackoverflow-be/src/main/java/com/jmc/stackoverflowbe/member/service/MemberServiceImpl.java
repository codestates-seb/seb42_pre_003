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
    private final MemberMapper mapper;

    @Override
    public Member createMember(MemberDto.Post post) {
        Member member = mapper.postDtoToMember(post);
        verifyExistEmail(post.getEmail());
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(MemberDto.Patch patch, long memberId) {
        Member existMember = findExistMemberById(memberId);
        Member member = mapper.patchDtoToMember(patch);

        Optional.ofNullable(member.getName())
                .ifPresent(content -> existMember.setName(patch.getName()));
        Optional.ofNullable(member.getAbout())
                .ifPresent(content -> existMember.setAbout(patch.getAbout()));
        Optional.ofNullable(member.getLocation())
                .ifPresent(content -> existMember.setLocation(patch.getLocation()));

        return memberRepository.save(existMember);
    }

    @Override
    public MemberDto.Response getMember(Long memberId) {
        MemberDto.Response response = mapper.memberToResponseDto(findExistMemberById(memberId));
        response.setIsMine(false);
        return response;
    }

    @Override
    public void deleteMember(Long memberId) {
        Member obtainedMember = findExistMemberById(memberId);
        obtainedMember.setState(MemberState.DELETED);
        memberRepository.save(obtainedMember);
    }

    @Override
    public void verifyExistEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
    }

    @Override
    public Member findExistMemberById(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findedMember = optionalMember
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        if (findedMember.getState() == MemberState.DELETED) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return findedMember;
    }
}
