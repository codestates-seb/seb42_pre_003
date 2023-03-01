package com.jmc.stackoverflowbe.vote.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

public class VoteDto {

    @Getter
    @NoArgsConstructor
    public static class Post {

        @Range(min = -1, max = 1)
        private int vote;

        @Positive
        private Long questionId;

        @Positive
        private Long answerId;

        @Builder
        public Post(@Range(min = -1, max = 1) int vote,
            @Positive Long questionId, @Positive Long answerId) {
            this.vote = vote;
            this.questionId = questionId;
            this.answerId = answerId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Patch {

        @Range(min = -1, max = 1)
        private Integer vote;

        @Builder
        public Patch(@Range(min = -1, max = 1) Integer vote) {
            this.vote = vote;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {

        private Long voteId;

        private Integer vote;

        private Long memberId;

        private Long questionId;

        private Long answerId;

        private LocalDateTime createdAt;

        @Builder
        public Response(Long voteId, Integer vote, Long memberId, Long questionId, Long answerId,
            LocalDateTime createdAt) {
            this.voteId = voteId;
            this.vote = vote;
            this.memberId = memberId;
            this.questionId = questionId;
            this.answerId = answerId;
            this.createdAt = createdAt;
        }
    }

    @Getter
    public static class VoteMultiResponseDto<T> {

        private List<T> data;

        public VoteMultiResponseDto(List<T> data) {
            this.data = data;
        }
    }
}
