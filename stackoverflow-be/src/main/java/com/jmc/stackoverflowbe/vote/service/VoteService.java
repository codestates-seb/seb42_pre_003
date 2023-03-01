package com.jmc.stackoverflowbe.vote.service;

import com.jmc.stackoverflowbe.vote.entity.Vote;
import java.util.List;

public interface VoteService {

    Vote createVote(Vote vote, Long memberId);

    Vote updateVote(Vote vote, Long voteId, Long memberId);

    List<Vote> getVotes(String qaType, Long qaId);

    void deleteVote(Long voteId, Long memberId);

    Vote findExistVoteById(Long voteId);
}
