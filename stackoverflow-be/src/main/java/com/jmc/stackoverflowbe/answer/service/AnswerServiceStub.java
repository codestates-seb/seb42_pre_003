package com.jmc.stackoverflowbe.answer.service;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.answer.entity.Answer.StateGroup;
import com.jmc.stackoverflowbe.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceStub implements AnswerService {
    @Override
    public Answer createAnswer(Answer answer){return null;}
    @Override
    public Answer updateAnswer(Answer answer){return null;}
    @Override
    public Answer getAnswer(Long answerId){
        Member member = new Member();
        Answer answer = new Answer();
        return Answer.builder()
            .answerId(1L)
            .answerContent("test content")
            .state(StateGroup.ACTIVE)
            .votes(1L)
            .memberId(1L)
            .questionId(1L)
            .build();
    }
    @Override
    public void deleteAnswer(Long answerId){

    }
    @Override
    public Answer findExistId(Long answerId){ return null;}

}
