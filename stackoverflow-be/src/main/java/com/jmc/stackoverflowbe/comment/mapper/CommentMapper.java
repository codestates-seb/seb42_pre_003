package com.jmc.stackoverflowbe.comment.mapper;

import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "questionId", target = "question.questionId")
    @Mapping(source = "answerId", target = "answer.answerId")
    Comment postDtoToComment(CommentDto.Post post);

    Comment patchDtoToComment(CommentDto.Patch patch);

    @Named("CTR")
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "member.name", target = "memberName")
    @Mapping(source = "question.questionId", target = "questionId")
    @Mapping(source = "answer.answerId", target = "answerId")
    CommentDto.Response commentToResponseDto(Comment comment);

    @IterableMapping(qualifiedByName = "CTR")
    List<CommentDto.Response> commentsToResponseDtos(List<Comment> comments);
}