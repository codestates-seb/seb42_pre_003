package com.jmc.stackoverflowbe.question.mapper;

import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question postDtoToQuestion(QuestionDto.Post post);
    Question patchDtoToQuestion(QuestionDto.Patch patch);
    QuestionDto.Response questionToResponseDto(Question question);
    List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions);

}
