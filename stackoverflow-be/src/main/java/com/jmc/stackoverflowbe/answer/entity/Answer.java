package com.jmc.stackoverflowbe.answer.entity;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column
    private Long memberId;

    @Column
    private Long questionId;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "QUESTION_ID")
//    private Question question;

    @Column
    private String answerContent;

    @Column
    private StateGroup state;

    @Column
    private Long votes;

    @Builder
    public Answer(Long answerId, Long memberId, Long questionId, String answerContent,
        StateGroup state,
        Long votes) {
        this.answerId = answerId;
        this.memberId = memberId;
        this.questionId = questionId;
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
