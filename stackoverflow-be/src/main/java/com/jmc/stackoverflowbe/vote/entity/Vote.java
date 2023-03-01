package com.jmc.stackoverflowbe.vote.entity;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.question.entity.Question;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vote extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Column
    private Integer vote;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public void setMember(Member member) {
        this.member = member;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Builder
    public Vote(Long voteId, Integer vote, Member member, Question question, Answer answer) {
        this.voteId = voteId;
        this.vote = vote;
        this.member = member;
        this.question = question;
        this.answer = answer;
    }
}
