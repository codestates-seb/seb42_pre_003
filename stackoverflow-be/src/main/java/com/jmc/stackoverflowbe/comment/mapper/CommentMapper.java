package com.jmc.stackoverflowbe.comment.mapper;

import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "questionId", target = "question.questionId")
    @Mapping(source = "answerId", target = "answer.answerId")
    Comment postDtoToComment(CommentDto.Post post);

    Comment patchDtoToComment(CommentDto.Patch patch);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "member.name", target = "memberName")
    @Mapping(source = "question.questionId", target = "questionId")
    @Mapping(source = "answer.answerId", target = "answerId")
    List<CommentDto.Response> commentsToResponseDtos(List<Comment> comments);
}