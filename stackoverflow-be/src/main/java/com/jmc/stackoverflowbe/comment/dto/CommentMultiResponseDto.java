package com.jmc.stackoverflowbe.comment.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class CommentMultiResponseDto<T> {
    private List<T> data;

    public CommentMultiResponseDto(List<T> data) {
        this.data = data;
    }
}
