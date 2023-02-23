package com.jmc.stackoverflowbe.question.service;

import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceStub implements QuestionService {
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
    public void deleteQuestion(Long id){

    }
    @Override
    public Question findExistId(Long id){ return null;}

}
