package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentBuilder;
import com.jmc.stackoverflowbe.comment.entity.Comment.CommentState;
import com.jmc.stackoverflowbe.comment.entity.Comment.QAState;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceStub implements CommentService {

    private final CommentBuilder stubComment = Comment.builder()
        .commentContent("Sample comment.")
        .memberId(1L)
        .memberName("kimcoding")
        .commentState(CommentState.ACTIVE);

    @Override
    public Comment createComment(Comment comment, QAState qaState, long qaId) {
        setQA(qaState);

        return this.stubComment
            .commentId(1L)
            .qaState(qaState)
            .build();
    }

    @Override
    public Comment updateComment(Comment comment, QAState qaState, long qaId) {
        setQA(qaState);

        return this.stubComment
            .commentId(comment.getCommentId())
            .qaState(qaState)
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

    private void setQA(QAState qaState) {
        System.out.println(qaState.getQaState().toString());
        if (qaState.getQaState().equals("질문")) {
            this.stubComment
                .questionId(1L)
                .answerId(null);
        } else {
            this.stubComment
                .questionId(null)
                .answerId(1L);
        }
    }
}
