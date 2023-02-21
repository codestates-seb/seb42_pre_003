package com.jmc.stackoverflowbe.qa.entity;

import com.jmc.stackoverflowbe.article.entity.Article;
import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QA extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String qaContent;

    @Column
    private QaGroup group;

    @Column
    private StateGroup state;

    @Column
    private Long votes;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @Builder
    public QA(Long id, String qaContent, QaGroup group, StateGroup state, Long votes,
        Member member,
        Article article) {
        this.id = id;
        this.qaContent = qaContent;
        this.group = group;
        this.state = state;
        this.votes = votes;
        this.member = member;
        this.article = article;
    }

    public static enum QaGroup{

        QUESTION("질문"),
        ANSWER("답변");

        @Getter
        private String state;

        QaGroup(String state) {
            this.state = state;
        }
    }
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
