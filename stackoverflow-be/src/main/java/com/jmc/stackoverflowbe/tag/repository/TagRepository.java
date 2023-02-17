package com.jmc.stackoverflowbe.tag.repository;

import com.jmc.stackoverflowbe.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
