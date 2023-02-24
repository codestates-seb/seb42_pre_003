package com.jmc.stackoverflowbe.answer.dto;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.global.audit.Auditable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AnswerDto {
    @Getter
    @NoArgsConstructor
    public static class Post {
        private Long questionId;
        private Long memberId;
        private String answerContent;

        @Builder
        public Post(Long questionId, Long memberId, String answerContent) {
            this.questionId = questionId;
            this.memberId = memberId;
            this.answerContent = answerContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        private String answerContent;

        @Builder
        public Patch(String answerContent) {
            this.answerContent = answerContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response extends Auditable {

        private Long answerId;
        // private Question Question;
        // private Member member;
        private Long questionId;
        private Long memberId;
        private String answerContent;
        private Integer votes;
        private Answer.StateGroup state;

        // @Builder
        // public Response(Long id, Article article, Member member, String qaContent,
        // Integer votes,
        // QaGroup group, StateGroup state) {
        // this.id = id;
        // this.article = article;
        // this.member = member;
        // this.qaContent = qaContent;
        // this.votes = votes;
        // this.group = group;
        // this.state = state;
        // }

        @Builder
        public Response(Long answerId, Long questionId, Long memberId, String answerContent,
                Integer votes, Answer.StateGroup state) {
            this.answerId = answerId;
            this.questionId = questionId;
            this.memberId = memberId;
            this.answerContent = answerContent;
            this.votes = votes;
            this.state = state;
        }
    }
}
