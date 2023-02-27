package com.jmc.stackoverflowbe.member.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.jmc.stackoverflowbe.global.exception.BusinessLogicException;
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
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    MemberMapper mapper;

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

    Member patchmember = Member.builder()
        .name("홍길동")
        .location("서울")
        .about("안녕하세요")
        .build();

    Member member = Member.builder()
        .email("kcs@gmail.com")
        .name("김철수")
        .build();

    Member updatedMEmber = Member.builder()
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
        given(mapper.postDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(member);
        given(memberRepository.findByEmail(Mockito.anyString())).willReturn(Optional.ofNullable(null));
        given(memberRepository.save(Mockito.any(Member.class))).willReturn(savedmember);
        assertDoesNotThrow(() -> memberService.createMember(post));
    }

    @DisplayName("회원 수정")
    @Test
    void updateMember() {
        given(memberRepository.findById(Mockito.anyLong())).willReturn(Optional.of(savedmember));
        given(mapper.patchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(patchmember);
        given(memberRepository.save(Mockito.any(Member.class))).willReturn(updatedMEmber);
        assertDoesNotThrow(() -> memberService.updateMember(patch, 1L));
    }

    @DisplayName("회원 조회")
    @Test
    void getMember() {
        given(memberRepository.findById(Mockito.anyLong())).willReturn(Optional.of(savedmember));
        given(mapper.memberToResponseDto(Mockito.any(Member.class))).willReturn(response);
        assertDoesNotThrow(() -> memberService.getMember(1L));
    }

    @DisplayName("회원 삭제")
    @Test
    void deleteMember() {
        given(memberRepository.findById(Mockito.anyLong())).willReturn(Optional.of(savedmember));
        given(memberRepository.save(Mockito.any(Member.class))).willReturn(deletedmember);
        assertDoesNotThrow(() -> memberService.deleteMember(1L));
    }
}
