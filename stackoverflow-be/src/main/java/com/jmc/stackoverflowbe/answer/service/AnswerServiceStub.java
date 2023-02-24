package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.entity.Answer.StateGroup;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceStub implements AnswerService {
    @Override
    public Answer createAnswer(Answer answer){return null;}
    @Override
    public Answer updateAnswer(Answer answer){return null;}

    @Override
    public List<Answer> getAnswers(Long questionId) {
        Answer answer1 = Answer.builder()
            .answerId(1L)
            .answerContent("test content")
            .state(StateGroup.ACTIVE)
            .votes(1L)
            .memberId(1L)
            .questionId(1L)
            .build();

        Answer answer2 = Answer.builder()
            .answerId(2L)
            .answerContent("test content")
            .state(StateGroup.ACTIVE)
            .votes(2L)
            .memberId(2L)
            .questionId(1L)
            .build();

        return List.of(answer1, answer2);
    }
    @Override
    public void deleteAnswer(Long answerId){

    }
    @Override
    public Answer findExistId(Long answerId){ return null;}

}
