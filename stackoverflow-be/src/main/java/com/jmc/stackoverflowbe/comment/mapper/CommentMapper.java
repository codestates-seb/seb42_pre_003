package com.jmc.stackoverflowbe.comment.mapper;

import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment PostDtoToComment(CommentDto.Post post);

    Comment PatchDtoToComment(CommentDto.Patch patch);

    CommentDto.Response commentToResponseDto(Comment comment);
}
