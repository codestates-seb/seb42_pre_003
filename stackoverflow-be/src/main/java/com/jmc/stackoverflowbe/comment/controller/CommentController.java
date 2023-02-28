package com.jmc.stackoverflowbe.comment.controller;

import com.jmc.stackoverflowbe.comment.dto.CommentDto;
import com.jmc.stackoverflowbe.comment.dto.CommentMultiResponseDto;
import com.jmc.stackoverflowbe.comment.entity.Comment;
import com.jmc.stackoverflowbe.comment.mapper.CommentMapper;
import com.jmc.stackoverflowbe.comment.service.CommentService;
import com.jmc.stackoverflowbe.global.security.auth.dto.Oauth2MemberDto;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper mapper;

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post post,
        Authentication authentication) {
        Oauth2MemberDto oAuth2User = (Oauth2MemberDto) authentication.getPrincipal();
        Comment comment = commentService.createComment(mapper.postDtoToComment(post),
            oAuth2User.getMemberId());
        URI location = UriCreator.createURI("/comments", comment.getCommentId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@Valid @RequestBody CommentDto.Patch patch,
        @Positive @PathVariable("comment-id") long commentId,
        Authentication authentication) {
        Oauth2MemberDto oAuth2User = (Oauth2MemberDto) authentication.getPrincipal();
        commentService.updateComment(mapper.patchDtoToComment(patch), commentId,
            oAuth2User.getMemberId());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getComments(@RequestParam String qaType, @RequestParam long qaId) {
        List<Comment> comments = commentService.getComments(qaType, qaId);

        return new ResponseEntity<>(new CommentMultiResponseDto<>(
            mapper.commentsToResponseDtos(comments)), HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@Positive @PathVariable("comment-id") long commentId,
        Authentication authentication) {
        Oauth2MemberDto oAuth2User = (Oauth2MemberDto) authentication.getPrincipal();
        commentService.deleteComment(commentId, oAuth2User.getMemberId());

        return ResponseEntity.noContent().build();
    }
}
