package com.jmc.stackoverflowbe.answer.mapper;

import com.jmc.stackoverflowbe.answer.dto.AnswerDto;
import com.jmc.stackoverflowbe.answer.dto.AnswerDto.AnswerMultiResponseDto;
import com.jmc.stackoverflowbe.answer.entity.Answer;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    Answer postDtoToAnswer(AnswerDto.Post post);

    Answer patchDtoToAnswer(AnswerDto.Patch patch);

    AnswerDto.Response answerToResponseDto(Answer answer);

    List<AnswerMultiResponseDto> answersToResponseDtos(List<Answer> answers);
}
