package com.jmc.stackoverflowbe.member.dto;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @NoArgsConstructor
    public static class Response extends Auditable {
        private Long id;
        private String email;
        private String name;
        private Boolean isMine;
        private String location;
        private String about;
        private MemberState state;

        @Builder
        public Response(Long id, String email, String name, Boolean isMine, String location,
            String about,
            MemberState state) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.isMine = isMine;
            this.location = location;
            this.about = about;
            this.state = state;
        }
    }
}
