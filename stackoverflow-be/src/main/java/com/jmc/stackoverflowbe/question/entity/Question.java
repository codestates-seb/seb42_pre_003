package com.jmc.stackoverflowbe.question.entity;

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
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String questionTitle;

    @Column
    private Long memberId;

    @Column
    private String questionContent;

    @Column
    private StateGroup state;

    @Column
    private Integer votes;

    @Column
    private Boolean selection;

    @Column
    private Long answers;

    @Column
    private Long views;

    @Builder
    public Question(Long questionId, String questionTitle, Long memberId, String questionContent,
        StateGroup state, Integer votes, Boolean selection, Long answers, Long views) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.memberId = memberId;
        this.questionContent = questionContent;
        this.state = state;
        this.votes = votes;
        this.selection = selection;
        this.answers = answers;
        this.views = views;
    }

    //    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "ARTICLE_ID")
//    private Article article;


    public static enum StateGroup {
        ACTIVE("활성"),
        INACTIVE("휴면"),
        DELETED("탈퇴");

        @Getter
        private String state;

        StateGroup(String state) {
            this.state = state;
        }
    }

}
