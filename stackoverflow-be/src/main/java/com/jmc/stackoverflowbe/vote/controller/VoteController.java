package com.jmc.stackoverflowbe.vote.controller;

import com.jmc.stackoverflowbe.global.security.auth.dto.LogInMemberDto;
import com.jmc.stackoverflowbe.global.security.auth.resolver.LoginMember;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import com.jmc.stackoverflowbe.vote.dto.VoteDto;
import com.jmc.stackoverflowbe.vote.entity.Vote;
import com.jmc.stackoverflowbe.vote.mapper.VoteMapper;
import com.jmc.stackoverflowbe.vote.service.VoteService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/votes")
@Validated
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    private final VoteMapper mapper;

    @PostMapping
    public ResponseEntity postVote(@Valid @RequestBody VoteDto.Post post,
        @LoginMember LogInMemberDto loginMember) {
        Vote vote = voteService.createVote(mapper.postDtoToVote(post),
            loginMember.getMemberId());
        URI location = UriCreator.createURI("/votes", vote.getVoteId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{vote-id}")
    public ResponseEntity patchVote(@Valid @RequestBody VoteDto.Patch patch,
        @Positive @PathVariable("vote-id") long voteId,
        @LoginMember LogInMemberDto loginMember) {
        voteService.updateVote(mapper.patchDtoToVote(patch), voteId,
            loginMember.getMemberId());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getVotes(@RequestParam String qaType, @RequestParam long qaId) {
        List<Vote> votes = voteService.getVotes(qaType, qaId);

        return new ResponseEntity<>(new VoteDto.VoteMultiResponseDto<>(
            mapper.votesToResponseDtos(votes)), HttpStatus.OK);
    }

    @DeleteMapping("/{vote-id}")
    public ResponseEntity deleteVote(@Positive @PathVariable("vote-id") long voteId,
        @LoginMember LogInMemberDto loginMember) {
        voteService.deleteVote(voteId, loginMember.getMemberId());

        return ResponseEntity.noContent().build();
    }
}
