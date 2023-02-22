package com.jmc.stackoverflowbe.qa.controller;

import com.jmc.stackoverflowbe.global.common.SingleResponseDto;
import com.jmc.stackoverflowbe.global.utils.UriCreator;
import com.jmc.stackoverflowbe.qa.dto.QADto;
import com.jmc.stackoverflowbe.qa.entity.QA;
import com.jmc.stackoverflowbe.qa.mapper.QAMapper;
import com.jmc.stackoverflowbe.qa.service.QAService;
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
@RequestMapping("/qas")
@RequiredArgsConstructor
public class QAController {
    private final QAService qaService;
    private final QAMapper mapper;

    @PostMapping
    public ResponseEntity postQA(@RequestBody QADto.Post post){
        qaService.createQA(mapper.PostDtoToQA(post));
        URI location = UriCreator.createURI("/qas", 1l);
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{qa-id}")
    public ResponseEntity patchQA(
        @PathVariable("qa-id") long qaId,
        @RequestBody QADto.Patch patch) {
        qaService.updateQA(mapper.PatchDtoToQA(patch));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{qa-id}")
    public ResponseEntity getQA(@PathVariable("qa-id") long qaId){
        QA qa = qaService.getQA(qaId);
        return new ResponseEntity(new SingleResponseDto<>(
            mapper.QAToResponseDto(qa)),
            HttpStatus.OK);
    }

    @DeleteMapping("/{qa-id}")
    public ResponseEntity deleteQA(@PathVariable("qa-id") long qaId){
        qaService.deleteQA(qaId);
        return ResponseEntity.noContent().build();
    }


}
