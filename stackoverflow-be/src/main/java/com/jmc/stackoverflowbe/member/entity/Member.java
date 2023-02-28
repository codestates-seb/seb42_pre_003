package com.jmc.stackoverflowbe.member.entity;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private  String picture;

    @Column(length = 210)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Column
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private MemberState state = MemberState.ACTIVE;

    @UpdateTimestamp
    @Column(name = "LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    @Builder
    public Member(Long memberId, String email, String name, String picture, String location,
        String about, MemberState state, LocalDateTime lastLoginTime) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.location = location;
        this.about = about;
        this.state = state;
        this.lastLoginTime = lastLoginTime;
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
