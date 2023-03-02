package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface CommentService {

    Comment createComment(Comment comment, Long memberId);

    Comment updateComment(Comment comment, Long commentId, Long memberId);

    List<Comment> getComments(String qaType, Long qaId);

    void deleteComment(Long commentId, Long memberId);

    Comment findExistCommentById(Long commentId);
}
