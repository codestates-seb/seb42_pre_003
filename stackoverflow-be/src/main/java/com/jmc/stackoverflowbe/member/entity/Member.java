package com.jmc.stackoverflowbe.member.entity;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 210)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column
    @Enumerated(value = EnumType.STRING)
    private MemberState state = MemberState.ACTIVE;

    @Builder
    public Member(Long id, String email, String name, String location, String about,
            MemberState state) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.location = location;
        this.about = about;
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
