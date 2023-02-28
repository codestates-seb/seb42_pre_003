package com.jmc.stackoverflowbe.member.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    Member member1 = Member.builder()
        .memberId(1L)
        .email("kcs@gmail.com")
        .name("김철수")
        .build();
    Member member2 = Member.builder()
        .memberId(2L)
        .email("bde@gmail.com")
        .name("바둑이")
        .build();

    Member member3 = Member.builder()
        .memberId(3L)
        .email("hgd@gmail.com")
        .name("홍길동")
        .build();

    // 모든 테스트 이전에 실행.
    @BeforeAll
    public void init() {
        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    @DisplayName("회원 저장")
    @Test
    void saveMember() {
        Member savedMember = memberRepository.save(member3);

        assertNotNull(savedMember);                                             // savedMember가 null이면 안 됨.
        assertTrue(member3.getMemberId().equals(savedMember.getMemberId()));    // savedMember와 member3의 필드가 동일한지 검증.
        assertTrue(member3.getEmail().equals(savedMember.getEmail()));
        assertTrue(member3.getName().equals(savedMember.getName()));
    }

    @DisplayName("회원 수정")
    @Test
    void updateMember() {
        // 수정 로직 대체
        member1.setEmail("kyh@gmail.com");
        member1.setName("김영희");
        member1.setState(MemberState.DORMANT);

        Member updatedMember = memberRepository.save(member1);

        assertNotNull(updatedMember);                                           // updateMember가 null이면 안 됨.
        assertTrue(member1.getMemberId().equals(updatedMember.getMemberId()));  // updateMember와 mebmer1의 필드가 동일한지 검증.
        assertTrue(member1.getEmail().equals(updatedMember.getEmail()));
        assertTrue(member1.getName().equals(updatedMember.getName()));
    }

    @DisplayName("식별자로 회원 조회")
    @Test
    void findMemberById() {
        Member obtainedMember = memberRepository.findById(2L).get();

        assertNotNull(obtainedMember);                                          // obtainedMember가 null이면 안 됨.
        assertTrue(member2.getMemberId().equals(obtainedMember.getMemberId())); // obtainedMember와 member2의 필드가 동일한지 검증.
        assertTrue(member2.getEmail().equals(obtainedMember.getEmail()));
        assertTrue(member2.getName().equals(obtainedMember.getName()));
    }

    @DisplayName("이메일로 회원 조회")
    @Test
    void findMemberByEmail() {
        Member obtainedMember = memberRepository.findByEmail("bde@gmail.com").get();

        assertNotNull(obtainedMember);                                          // obtainedMember가 null이면 안 됨.
        assertTrue(member2.getMemberId().equals(obtainedMember.getMemberId())); // obtainedMember와 member2의 필드가 동일한지 검증
        assertTrue(member2.getEmail().equals(obtainedMember.getEmail()));
        assertTrue(member2.getName().equals(obtainedMember.getName()));
    }
}
