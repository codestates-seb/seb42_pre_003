package com.jmc.stackoverflowbe.question.controller;

import com.jmc.stackoverflowbe.global.common.SingleResponseDto;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import com.jmc.stackoverflowbe.question.dto.QuestionDto;
import com.jmc.stackoverflowbe.question.entity.Question;
import com.jmc.stackoverflowbe.question.mapper.QuestionMapper;
import com.jmc.stackoverflowbe.question.service.QuestionService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.Post post){
        questionService.createQuestion(mapper.PostDtoToQA(post));
        URI location = UriCreator.createURI("/qas", 1l);
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{qa-id}")
    public ResponseEntity patchQA(
        @PathVariable("qa-id") long qaId,
        @RequestBody QuestionDto.Patch patch) {
        questionService.updateQA(mapper.PatchDtoToQA(patch));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{qa-id}")
    public ResponseEntity getQA(@PathVariable("qa-id") long qaId){
        Question question = questionService.getQA(qaId);
        return new ResponseEntity(new SingleResponseDto<>(
            mapper.QAToResponseDto(question)),
            HttpStatus.OK);
    }

    @DeleteMapping("/{qa-id}")
    public ResponseEntity deleteQA(@PathVariable("qa-id") long qaId){
        questionService.deleteQA(qaId);
        return ResponseEntity.noContent().build();
    }


}
