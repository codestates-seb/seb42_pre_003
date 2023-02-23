package com.jmc.stackoverflowbe.comment.dto;

import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.global.audit.Auditable;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDto {

    @Getter
    @NoArgsConstructor
    public static class Post {

        @NotBlank
        private String commentContent;

        private Long questionId;

        private Long answerId;

        @Builder
        public Post(@NotBlank String commentContent, Long questionId, Long answerId) {
            this.commentContent = commentContent;
            this.questionId = questionId;
            this.answerId = answerId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        @NotBlank
        private String commentContent;

        @Builder
        public Patch(@NotBlank String commentContent) {
            this.commentContent = commentContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response extends Auditable {

        private Long commentId;

        private String commentContent;

        private Long memberId;

        private String memberName;

        private Long questionId;

        private Long answerId;

        private CommentState commentState;

        @Builder
        public Response(Long commentId, String commentContent, Long memberId, String memberName,
            Long questionId, Long answerId, CommentState commentState) {
            this.commentId = commentId;
            this.commentContent = commentContent;
            this.memberId = memberId;
            this.memberName = memberName;
            this.questionId = questionId;
            this.answerId = answerId;
            this.commentState = commentState;
        }
    }
}
