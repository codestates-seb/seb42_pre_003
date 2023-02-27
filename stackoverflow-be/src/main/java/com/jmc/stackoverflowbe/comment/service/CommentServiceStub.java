package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentBuilder;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import java.util.List;
import org.springframework.stereotype.Service;

public class CommentServiceStub {

    private final CommentBuilder stubComment1 = Comment.builder()
        .commentId(1L)
        .commentContent("Sample comment.")
        .memberId(1L)
        .memberName("kimcoding")
        .questionId(1L)
        .answerId(null)
        .commentState(CommentState.ACTIVE);

    private final CommentBuilder stubComment2 = Comment.builder()
        .commentId(2L)
        .commentContent("Sample comment 2.")
        .memberId(1L)
        .memberName("kimcoding")
        .questionId(1L)
        .answerId(null)
        .commentState(CommentState.ACTIVE);

//    @Override
    public Comment createComment(Comment comment) {
        return this.stubComment1
            .build();
    }

//    @Override
    public Comment updateComment(Comment comment) {
        return Comment.builder()
            .commentId(comment.getCommentId())
            .commentContent("Sample comment.")
            .build();
    }

//    @Override
    public List<Comment> getComments(String qaType, Long qaId) {
        return List.of(stubComment1.build(), stubComment2.build());
    }

//    @Override
    public void deleteComment(Long commentId) {
    }
}
