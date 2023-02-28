package com.jmc.stackoverflowbe.answer.entity;

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
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Column
    private String answerContent;

    @Column
    private StateGroup state;

    @Column
    private Long votes;

    @Builder
    public Answer(Long answerId, Member member, Question question, String answerContent,
        StateGroup state, Long votes) {
        this.answerId = answerId;
        this.member = member;
        this.question = question;
        this.answerContent = answerContent;
        this.state = state;
        this.votes = votes;
    }

    public static enum StateGroup {
        ACTIVE("활성"),
        INACTIVE("비활성"),
        DELETED("삭제");

        @Getter
        private String state;

        StateGroup(String state) {
            this.state = state;
        }
    }
}
