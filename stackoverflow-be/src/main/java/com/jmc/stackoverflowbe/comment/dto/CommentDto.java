package com.jmc.stackoverflowbe.comment.dto;

import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDto {

    @Getter
    @NoArgsConstructor
    public static class Post {

        private String commentContent;

//        private Long memberId;

//        private Long qaId;

        @Builder
        public Post(String commentContent) {
            this.commentContent = commentContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        private Long commentId;

        private String commentContent;

//        private Long memberId;

//        private Long qaId;

        @Builder
        public Patch(Long commentId, String commentContent) {
            this.commentId = commentId;
            this.commentContent = commentContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {

        private Long commentId;

        private String commentContent;

//        private Long memberId;

//        private Long qaId;

        private CommentState commentState;

        private Timestamp createdAt;

        private Timestamp modifiedAt;

        @Builder
        public Response(Long commentId, String commentContent, CommentState commentState) {
            this.commentId = commentId;
            this.commentContent = commentContent;
            this.commentState = commentState;
        }
    }
}
