package com.jmc.stackoverflowbe.answer.mapper;

import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto.AnswerMultiResponseDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "questionId", target = "question.questionId")
    Answer postDtoToAnswer(AnswerDto.Post post);

    Answer patchDtoToAnswer(AnswerDto.Patch patch);

    @Named("ATR")
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "question.questionId", target = "questionId")
    AnswerDto.Response answerToResponseDto(Answer answer);

    @IterableMapping(qualifiedByName = "ATR")
    List<AnswerDto.Response> answersToResponseDtos(List<Answer> answers);
}
