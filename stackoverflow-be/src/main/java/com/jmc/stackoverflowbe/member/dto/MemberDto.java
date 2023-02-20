package com.jmc.stackoverflowbe.member.dto;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.member.entity.Member.MemberState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    public static class Post {

        private String email;
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

        private String email;
        private String name;

        @Builder
        public Patch(String email, String name) {
            this.email = email;
            this.name = name;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response extends Auditable {

        private String email;
        private String name;
        private Boolean isMine;
        private MemberState state;

        @Builder
        public Response(String email, String name, boolean isMine, MemberState state) {
            this.email = email;
            this.name = name;
            this.isMine = isMine;
            this.state = state;
        }
    }
}
