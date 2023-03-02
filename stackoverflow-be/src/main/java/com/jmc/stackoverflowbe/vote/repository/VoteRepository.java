package com.jmc.stackoverflowbe.vote.repository;

import com.jmc.stackoverflowbe.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
