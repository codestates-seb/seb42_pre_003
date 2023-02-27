package com.jmc.stackoverflowbe.question.dto;

import com.jmc.stackoverflowbe.global.audit.Auditable;
import com.jmc.stackoverflowbe.question.entity.Question.StateGroup;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionDto {
    @Getter
    @NoArgsConstructor
    public static class Post {
        private String questionTitle;
        private String questionContent;

        @Builder
        public Post(String questionTitle, String questionContent) {
            this.questionTitle = questionTitle;
            this.questionContent = questionContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        private Long questionId;
        private String questionTitle;
        private String questionContent;

        public void setQuestionId(Long questionId){
            this.questionId = questionId;}
        @Builder
        public Patch(String questionTitle, String questionContent) {
            this.questionTitle = questionTitle;
            this.questionContent = questionContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response extends Auditable {
        private Long questionId;
        private String questionTitle;
        private String questionContent;
        private Long memberId;
        private StateGroup state;
        private Integer votes;
        private Boolean selection;
        private Long answers;
        private Long views;

        @Builder
        public Response(Long questionId, String questionTitle, String questionContent,
            Long memberId,
            StateGroup state, Integer votes, Boolean selection, Long answers,
            Long views) {
            this.questionId = questionId;
            this.questionTitle = questionTitle;
            this.questionContent = questionContent;
            this.memberId = memberId;
            this.state = state;
            this.votes = votes;
            this.selection = selection;
            this.answers = answers;
            this.views = views;
        }
    }
}
