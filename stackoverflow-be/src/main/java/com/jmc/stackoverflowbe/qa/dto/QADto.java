package com.jmc.stackoverflowbe.qa.dto;

import com.jmc.stackoverflowbe.article.entity.Article;
import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.member.entity.Member;
import com.jmc.stackoverflowbe.qa.entity.QA.QaGroup;
import com.jmc.stackoverflowbe.qa.entity.QA.StateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QADto {
    @Getter
    @NoArgsConstructor
    public static class Post {
        private Article article;
        private Member member;
        private String qaContent;
        private Integer votes;

        @Builder
        public Post(Article article, Member member, String qaContent, Integer votes) {
            this.article = article;
            this.member = member;
            this.qaContent = qaContent;
            this.votes = votes;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        private String qaContent;
        private Integer votes;

        public Patch(String qaContent, Integer votes) {
            this.qaContent = qaContent;
            this.votes = votes;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response extends Auditable {

        private Article article;
        private Member member;
        private String qaContent;
        private Integer votes;
        private QaGroup group;
        private StateGroup state;

        @Builder
        public Response(Article article, Member member, String qaContent, Integer votes,
            QaGroup group,
            StateGroup state) {
            this.article = article;
            this.member = member;
            this.qaContent = qaContent;
            this.votes = votes;
            this.group = group;
            this.state = state;
        }
    }
}
