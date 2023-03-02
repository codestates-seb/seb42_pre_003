package com.jmc.stackoverflowbe.question.mapper;

import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface QuestionMapper {


    Question postDtoToQuestion(QuestionDto.Post post);
    Question patchDtoToQuestion(QuestionDto.Patch patch);
    @Named("QTR")
    @Mapping(source = "member.memberId", target = "memberId")
    QuestionDto.Response questionToResponseDto(Question question);
    @IterableMapping(qualifiedByName = "QTR")
    List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions);

}
