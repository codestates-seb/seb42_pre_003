package com.jmc.stackoverflowbe.comment.dto;

import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
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

        @Positive
        private Long qaId;

        @Builder
        public Post(String commentContent, Long qaId) {
            this.commentContent = commentContent;
            this.qaId = qaId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        @Positive
        private Long commentId;

        @NotBlank
        private String commentContent;

        @Positive
        private Long qaId;

        @Builder
        public Patch(Long commentId, String commentContent, Long qaId) {
            this.commentId = commentId;
            this.commentContent = commentContent;
            this.qaId = qaId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {

        private Long commentId;

        private String commentContent;

        private Long qaId;

        private CommentState commentState;

        private Timestamp createdAt;

        private Timestamp modifiedAt;

        @Builder
        public Response(Long commentId, String commentContent, Long qaId,
            CommentState commentState) {
            this.commentId = commentId;
            this.commentContent = commentContent;
            this.qaId = qaId;
            this.commentState = commentState;
        }
    }
}
