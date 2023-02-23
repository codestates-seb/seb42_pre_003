package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.QAState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {

    Comment createComment(Comment comment, QAState qaState, long qaId);

    Comment updateComment(Comment comment, QAState qaState, long qaId);

    Comment getComment(Long commentId);

    void deleteComment(Long commentId);
}
