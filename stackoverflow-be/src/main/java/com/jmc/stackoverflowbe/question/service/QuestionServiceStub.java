package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceStub implements QuestionService {
    private Question stubQuestion1;
    private Question stubQuestion2;
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
    @Override
    public Question createQuestion(Question question){return null;}
    @Override
    public Question updateQuestion(Question question){return null;}
    @Override
    public Question getQuestion(Long id){
        return Question.builder()
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

    @Override
    public Page<Question> getQuestions(int page, String sort){
        return new PageImpl<>(List.of(stubQuestion1,stubQuestion2),
            PageRequest.of(0,15, Sort.by(sort).descending()),2);
    }


    @Override
    public void deleteQuestion(Long id){

    }
    @Override
    public Question findExistId(Long id){ return null;}

}
