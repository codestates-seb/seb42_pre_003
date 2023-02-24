package com.jmc.stackoverflowbe.comment.mapper;

import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment postDtoToComment(CommentDto.Post post);

    Comment patchDtoToComment(CommentDto.Patch patch);

    List<CommentDto.Response> commentsToResponseDtos(List<Comment> comments);
}