package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface CommentService {

    Comment createComment(Comment comment, String email);

    Comment updateComment(Comment comment, Long commentId, String email);

    List<Comment> getComments(String qaType, Long qaId);

    void deleteComment(Long commentId, String email);

    Comment findExistCommentById(Long commentId);
}
