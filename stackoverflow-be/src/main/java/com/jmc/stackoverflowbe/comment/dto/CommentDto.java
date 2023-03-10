package com.jmc.stackoverflowbe.comment.dto;

import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.global.audit.Auditable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDto {

    @Getter
    @NoArgsConstructor
    public static class Post {

        @NotBlank
        private String commentContent;

        @Positive
        private Long questionId;

        @Positive
        private Long answerId;

        @Builder
        public Post(@NotBlank String commentContent,
            @Positive Long questionId, @Positive Long answerId) {
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

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        @Builder
        public Response(Long commentId, String commentContent, Long memberId, String memberName,
            Long questionId, Long answerId, CommentState commentState, LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
            this.commentId = commentId;
            this.commentContent = commentContent;
            this.memberId = memberId;
            this.memberName = memberName;
            this.questionId = questionId;
            this.answerId = answerId;
            this.commentState = commentState;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }
    }
}
