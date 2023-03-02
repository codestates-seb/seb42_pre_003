package com.jmc.stackoverflowbe.member.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jmc.stackoverflowbe.global.security.auth.dto.LogInMemberDto;
import com.jmc.stackoverflowbe.member.dto.MemberDto;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import com.jmc.stackoverflowbe.member.mapper.MemberMapper;
import com.jmc.stackoverflowbe.member.repository.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    MemberMapper mapper;

    LogInMemberDto loginMember = LogInMemberDto.builder()
        .memberId(1L)
        .email("kcs@gmail.com")
        .build();

    MemberDto.Post post = MemberDto.Post.builder()
        .email("kcs@gmail.com")
        .name("김철수")
        .build();

    MemberDto.Patch patch = MemberDto.Patch.builder()
        .name("홍길동")
        .location("서울")
        .about("안녕하세요")
        .build();

    Member savedmember = Member.builder()
        .memberId(1L)
        .email("kcs@gmail.com")
        .name("김철수")
        .state(MemberState.ACTIVE)
        .build();

    Member posthmember = Member.builder()
        .email("kcs@gmail.com")
        .name("김철수")
        .build();

    Member patchmember = Member.builder()
        .memberId(1L)
        .name("홍길동")
        .location("서울")
        .about("안녕하세요")
        .build();

    Member member = Member.builder()
        .email("kcs@gmail.com")
        .name("김철수")
        .build();

    Member updatedMember = Member.builder()
        .memberId(1L)
        .email("kcs@gmail.com")
        .name("홍길동")
        .location("서울")
        .about("안녕하세요")
        .state(MemberState.ACTIVE)
        .build();

    Member deletedmember = Member.builder()
        .memberId(1L)
        .email("kcs@gmail.com")
        .name("김철수")
        .state(MemberState.DELETED)
        .build();

    MemberDto.Response response = MemberDto.Response.builder()
        .memberId(1L)
        .name("홍길동")
        .location("서울")
        .about("안녕하세요")
        .build();

    @DisplayName("회원 생성")
    @Test
    void createMember() {
        // memberRepository.findByEmail()가 Optional null을 반환.
        given(memberRepository.findByEmail(Mockito.anyString())).willReturn(
            Optional.ofNullable(null));

        // memberRepository.save()가 savedmember를 반환.
        given(memberRepository.save(Mockito.any(Member.class))).willReturn(savedmember);

        // createMember()가 예외를 발생하지 않아야 함.
        assertDoesNotThrow(() -> memberService.createMember(posthmember));
    }

    @DisplayName("회원 수정")
    @Test
    void updateMember() {
        // memberRepository.findById()가 Optional savedmember를 반환
        given(memberRepository.findById(Mockito.anyLong())).willReturn(
            Optional.ofNullable(savedmember));

        // memberRepository.save()가 updatedMember를 반환.
        given(memberRepository.save(Mockito.any(Member.class))).willReturn(updatedMember);

        // updateMember()가 예외를 발생하지 않아야 함.
        assertDoesNotThrow(() -> memberService.updateMember(patchmember));
    }

    @DisplayName("회원 조회")
    @Test
    void getMember() {
        // memberRepository.findById()가 Optional savedmember를 반환.
        given(memberRepository.findById(Mockito.anyLong())).willReturn(Optional.of(savedmember));

        // getMember()가 예외를 발생하지 않아야 함.
        assertDoesNotThrow(() -> memberService.getMember(1L));
    }

    @DisplayName("회원 삭제")
    @Test
    void deleteMember() {
        // memberRepository.findById()가 Optioinal savedmember를 반환.
        given(memberRepository.findById(Mockito.anyLong())).willReturn(Optional.of(savedmember));

        // memberRepository.save()가 deletedmember를 반환.
        given(memberRepository.save(Mockito.any(Member.class))).willReturn(deletedmember);

        // deleteMember()가 예외를 발생하지 않아야 함.
        assertDoesNotThrow(() -> memberService.deleteMember(1L));
    }
}
