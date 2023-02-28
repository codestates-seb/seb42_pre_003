package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.entity.Answer.StateGroup;
import java.util.List;
import org.springframework.stereotype.Service;

public class AnswerServiceStub {

    public Answer createAnswer(Answer answer) {
        return null;
    }

    public Answer updateAnswer(Answer answer) {
        return null;
    }

    public List<Answer> getAnswers(Long questionId) {
        Answer answer1 = Answer.builder()
            .answerId(1L)
            .answerContent("test content")
            .state(StateGroup.ACTIVE)
            .votes(1L)
            .build();

        Answer answer2 = Answer.builder()
            .answerId(2L)
            .answerContent("test content")
            .state(StateGroup.ACTIVE)
            .votes(2L)
            .build();

        return List.of(answer1, answer2);
    }

    public void deleteAnswer(Long answerId) {

    }

    public Answer findExistId(Long answerId) {
        return null;
    }

}
