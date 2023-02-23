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
        private Long memberId;
        private String questionContent;

        @Builder
        public Post(String questionTitle, Long memberId, String questionContent) {
            this.questionTitle = questionTitle;
            this.memberId = memberId;
            this.questionContent = questionContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        private Long questionId;
        private String questionTitle;
        private String questionContent;
        private StateGroup state;
        private Boolean selection;

        @Builder
        public Patch(String questionTitle, Long questionId, String questionContent,
            StateGroup state, Boolean selection) {
            this.questionTitle = questionTitle;
            this.questionId = questionId;
            this.questionContent = questionContent;
            this.state = state;
            this.selection = selection;
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
