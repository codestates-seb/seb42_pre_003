package com.jmc.stackoverflowbe.member.dto;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        private String email;
        @NotBlank
        @Pattern(regexp = "^[0-9A-Za-z가-힣]{2,30}$",
            message = "숫자, 영어, 한글이 포함된 최대 30글자 이름만 사용 가능합니다.")
        private String name;

        @Builder
        public Post(String email, String name) {
            this.email = email;
            this.name = name;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {
        @Pattern(regexp = "^[0-9A-Za-z가-힣]{2,30}$",
            message = "숫자, 영어, 한글이 포함된 최대 30글자 이름만 사용 가능합니다.")
        private String name;

        private String location;

        private String about;

        @Builder
        public Patch(String name, String location, String about) {
            this.name = name;
            this.location = location;
            this.about = about;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response extends Auditable {
        private Long memberId;
        private String email;
        private String name;
        private Boolean isMine;
        private String location;
        private String about;
        private MemberState state;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private LocalDateTime lastLoginTime;

        @Builder
        public Response(Long memberId, String email, String name, Boolean isMine, String location,
            String about, MemberState state, LocalDateTime createdAt, LocalDateTime modifiedAt,
            LocalDateTime lastLoginTime) {
            this.memberId = memberId;
            this.email = email;
            this.name = name;
            this.isMine = isMine;
            this.location = location;
            this.about = about;
            this.state = state;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
            this.lastLoginTime = lastLoginTime;
        }
    }
}
