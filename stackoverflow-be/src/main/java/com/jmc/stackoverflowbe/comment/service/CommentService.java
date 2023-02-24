package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;

public interface CommentService {

    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    List<Comment> getComments(String qaType, Long qaId);

    void deleteComment(Long commentId);
}
