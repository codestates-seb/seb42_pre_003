package com.jmc.stackoverflowbe.vote.repository;

import com.jmc.stackoverflowbe.vote.entity.Vote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByQuestionQuestionId(Long qaId);

    List<Vote> findAllByAnswerAnswerId(Long qaId);
}
