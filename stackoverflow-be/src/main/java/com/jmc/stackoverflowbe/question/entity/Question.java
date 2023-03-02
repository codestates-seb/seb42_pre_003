package com.jmc.stackoverflowbe.question.entity;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.member.entity.Member;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Builder
@Getter
@Setter
@Entity
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String questionTitle;

//    @Column
//    private Long memberId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column
    private String questionContent;

    @Column
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private StateGroup state = StateGroup.ACTIVE;

    @Column
    private Integer votes;

    @Column
    private Boolean selection;

    @Column
    private Long answers;

    @Column
    private Long views;

    @Builder
    public Question(Long questionId, String questionTitle, Member member, String questionContent,
        StateGroup state, Integer votes, Boolean selection, Long answers, Long views) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.member = member;
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
