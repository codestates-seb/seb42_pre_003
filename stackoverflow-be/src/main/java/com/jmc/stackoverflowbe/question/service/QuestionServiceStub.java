package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.dto.QuestionDto.Response;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//@Service
public class QuestionServiceStub implements QuestionService {
    private Question stubQuestion1;
    private Question stubQuestion2;
    private  QuestionMapper mapper;
    @PostConstruct
    public void init(){
        stubQuestion1 = Question.builder()
            .questionId(0L)
            .questionTitle("Question1 title for stub")
            .memberId(0L)
            .questionContent("Question1 contents for stub")
            .state(StateGroup.ACTIVE)
            .votes(0)
            .selection(false)
            .answers(0L)
            .views(0L)
            .build();
        stubQuestion2 = Question.builder()
            .questionId(1L)
            .questionTitle("Question2 title for stub")
            .memberId(1L)
            .questionContent("Question2 contents for stub")
            .state(StateGroup.ACTIVE)
            .votes(2)
            .selection(false)
            .answers(2L)
            .views(2L)
            .build();

    }
    //@Override
    public Question createQuestion(QuestionDto.Post post){return null;}
    //@Override
    public Question updateQuestion(QuestionDto.Patch patch, Long questionId){return null;}
    //@Override
    public QuestionDto.Response getQuestion(Long id){
        return QuestionDto.Response.builder()
            .questionId(0L)
            .questionTitle("Question title for stub")
            .memberId(0L)
            .questionContent("Question contents for stub")
            .state(StateGroup.ACTIVE)
            .votes(0)
            .selection(false)
            .answers(0L)
            .views(0L)
            .build();

    }

    //@Override
    public Page<Response> getQuestions(int page, String sort){
        return new PageImpl<>(
            List.of(
                mapper.questionToResponseDto(stubQuestion1),
                mapper.questionToResponseDto(stubQuestion2)),
            PageRequest.of(0,15, Sort.by(sort).descending()),2);
    }


    //@Override
    public void deleteQuestion(Long id){

    }
    //@Override
    public Question findExistQuestionById(Long id){ return null;}

}
