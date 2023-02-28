package com.jmc.stackoverflowbe.answer.mapper;

import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto.AnswerMultiResponseDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "questionId", target = "question.questionId")
    Answer postDtoToAnswer(AnswerDto.Post post);

    Answer patchDtoToAnswer(AnswerDto.Patch patch);

    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "question.questionId", target = "questionId")
    List<AnswerDto.Response> answersToResponseDtos(List<Answer> answers);
}
