package com.jmc.stackoverflowbe.comment.controller;

import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.entity.Comment.QAState;
import com.jmc.stackoverflowbe.comment.mapper.CommentMapper;
import com.jmc.stackoverflowbe.comment.service.CommentService;
import com.jmc.stackoverflowbe.global.common.SingleResponseDto;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post post,
        @RequestParam QAState qaState,
        @Positive @RequestParam long qaId) {
        Comment comment = commentService.createComment(
            mapper.postDtoToComment(post), qaState, qaId);
        URI location = UriCreator.createURI("/comments", comment.getCommentId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@Valid @RequestBody CommentDto.Patch patch,
        @Positive @PathVariable("comment-id") long commentId,
        @RequestParam QAState qaState,
        @Positive @RequestParam long qaId) {
        commentService.updateComment(mapper.patchDtoToComment(patch), qaState, qaId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(@Positive @PathVariable("comment-id") long commentId) {
        Comment comment = commentService.getComment(commentId);

        return new ResponseEntity<>(new SingleResponseDto<>(
            mapper.commentToResponseDto(comment)),
            HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@Positive @PathVariable("comment-id") long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.noContent().build();
    }
}
