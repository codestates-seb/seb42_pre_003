package com.jmc.stackoverflowbe.vote.mapper;

import com.jmc.stackoverflowbe.vote.dto.VoteDto;
import com.jmc.stackoverflowbe.vote.dto.VoteDto.Response;
import com.jmc.stackoverflowbe.vote.entity.Vote;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(source = "questionId", target = "question.questionId")
    @Mapping(source = "answerId", target = "answer.answerId")
    Vote postDtoToVote(VoteDto.Post post);

    Vote patchDtoToVote(VoteDto.Patch patch);

    @Named("VTR")
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "question.questionId", target = "questionId")
    @Mapping(source = "answer.answerId", target = "answerId")
    VoteDto.Response voteToResponseDto(Vote vote);

    @IterableMapping(qualifiedByName = "VTR")
    List<Response> votesToResponseDtos(List<Vote> votes);
}
