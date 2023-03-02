package com.jmc.stackoverflowbe.member.repository;

import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberIdAndStateIs(Long id, MemberState state);
    Optional<Member> findByEmailAndStateIs(String email, MemberState state);
}
