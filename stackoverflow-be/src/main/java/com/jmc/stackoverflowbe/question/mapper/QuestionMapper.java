package com.jmc.stackoverflowbe.question.mapper;

import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question PostDtoToQA(QuestionDto.Post post);
    Question PatchDtoToQA(QuestionDto.Patch patch);
    QuestionDto.Response QAToResponseDto(Question question);

}
