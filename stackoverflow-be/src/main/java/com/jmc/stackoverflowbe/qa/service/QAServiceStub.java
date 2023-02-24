package com.jmc.stackoverflowbe.qa.service;

import com.jmc.stackoverflowbe.article.entity.Article;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.qa.entity.QA;
import com.jmc.stackoverflowbe.qa.entity.QA.QaGroup;
import com.jmc.stackoverflowbe.qa.entity.QA.StateGroup;
import org.springframework.stereotype.Service;

@Service
public class QAServiceStub implements QAService{
    @Override
    public QA createQA(QA qa){return null;}
    @Override
    public QA updateQA(QA qa){return null;}
    @Override
    public QA getQA(Long id){
        Member member = new Member();
        Article article = new Article();
        return QA.builder()
            .id(id)
            .qaContent("test content")
            .qaGroup(QaGroup.QUESTION)
            .state(StateGroup.ACTIVE)
            .votes(0L)
            .memberId(0L)
            .articleId(0L)
            .build();
    }
    @Override
    public void deleteQA(Long id){

    }
    @Override
    public QA findExistId(Long id){ return null;}

}
