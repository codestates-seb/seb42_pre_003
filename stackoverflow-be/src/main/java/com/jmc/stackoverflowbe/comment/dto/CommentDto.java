package com.jmc.stackoverflowbe.comment.dto;

import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.entity.Comment.QAState;
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

        @NotNull
        private QAState qaState;

        @Builder
        public Post(String commentContent, QAState qaState) {
            this.commentContent = commentContent;
            this.qaState = qaState;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        @Positive
        private Long commentId;

        @NotBlank
        private String commentContent;

        @NotNull
        private QAState qaState;

        @Builder
        public Patch(Long commentId, String commentContent, QAState qaState) {
            this.commentId = commentId;
            this.commentContent = commentContent;
            this.qaState = qaState;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {

        private Long commentId;

        private String commentContent;

        private Long memberId;

        private String memberName;

        private Long questionId;

        private Long answerId;

        private CommentState commentState;

        private QAState qaState;

        private Timestamp createdAt;

        private Timestamp modifiedAt;

        @Builder
        public Response(Long commentId, String commentContent, Long memberId, String memberName,
            Long questionId, Long answerId, CommentState commentState, QAState qaState) {
            this.commentId = commentId;
            this.commentContent = commentContent;
            this.memberId = memberId;
            this.memberName = memberName;
            this.questionId = questionId;
            this.answerId = answerId;
            this.commentState = commentState;
            this.qaState = qaState;
        }
    }
}
