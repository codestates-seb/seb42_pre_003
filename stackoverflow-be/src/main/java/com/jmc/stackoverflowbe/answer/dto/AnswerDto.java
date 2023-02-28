package com.jmc.stackoverflowbe.answer.dto;

import com.jmc.stackoverflowbe.answer.entity.Answer;
import com.jmc.stackoverflowbe.global.audit.Auditable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AnswerDto {

    @Getter
    @NoArgsConstructor
    public static class Post {

        @Positive
        private Long questionId;

        @NotBlank
        private String answerContent;

        @Builder
        public Post(@Positive Long questionId, @NotBlank String answerContent) {
            this.questionId = questionId;
            this.answerContent = answerContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        @NotBlank
        private String answerContent;

        @Builder
        public Patch(@NotBlank String answerContent) {
            this.answerContent = answerContent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response extends Auditable {

        private Long answerId;
        private Long questionId;
        private Long memberId;
        private String answerContent;
        private Integer votes;
        private Answer.StateGroup state;

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

    @Getter
    public static class AnswerMultiResponseDto<T> {

        private List<T> data;

        public AnswerMultiResponseDto(List<T> data) {
            this.data = data;
        }
    }
}
