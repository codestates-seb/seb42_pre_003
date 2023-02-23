package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {

    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    Comment getComment(Long commentId);

    void deleteComment(Long commentId);
}
