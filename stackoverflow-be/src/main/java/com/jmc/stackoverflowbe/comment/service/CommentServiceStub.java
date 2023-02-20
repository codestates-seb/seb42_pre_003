package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentBuilder;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceStub implements CommentService {

    private final CommentBuilder stubComment = Comment.builder()
        .commentContent("Sample comment.")
        .commentState(CommentState.ACTIVE);

    @Override
    public Comment createComment(Comment comment) {
        return this.stubComment
            .commentId(1L)
            .build();
    }

    @Override
    public Comment updateComment(Comment comment) {
        return this.stubComment
            .commentId(comment.getCommentId())
            .build();
    }

    @Override
    public Comment getComment(Long commentId) {
        return this.stubComment
            .commentId(commentId)
            .build();
    }

    @Override
    public void deleteComment(Long commentId) {

    }
}
