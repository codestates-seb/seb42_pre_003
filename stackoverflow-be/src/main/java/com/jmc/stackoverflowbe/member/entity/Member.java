package com.jmc.stackoverflowbe.member.entity;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Member extends Auditable {

    private Long id;

    private String email;

    private String name;

    private MemberState state;

    @Builder
    public Member(Long id, String email, String name, MemberState state) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.state = state;
    }

    public static enum MemberState {
        ACTIVE("활성"),
        DORMANT("휴면"),
        DELETED("탈퇴");

        @Getter
        private String state;

        MemberState(String state) {
            this.state = state;
        }
    }
}
