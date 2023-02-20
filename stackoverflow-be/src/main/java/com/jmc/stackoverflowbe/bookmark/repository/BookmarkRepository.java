package com.jmc.stackoverflowbe.bookmark.repository;

import com.jmc.stackoverflowbe.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
