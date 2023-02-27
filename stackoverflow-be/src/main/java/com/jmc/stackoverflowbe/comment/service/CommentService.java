package com.jmc.stackoverflowbe.comment.service;

import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;

public interface CommentService {

    Comment createComment(CommentDto.Post post);

    Comment updateComment(CommentDto.Patch patch, Long commentId);

    List<CommentDto.Response> getComments(String qaType, Long qaId);

    void deleteComment(Long commentId);

    Comment findExistCommentById(Long commentId);
}
