package com.jmc.stackoverflowbe.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EMAIL_EXISTS(409, "Email Already Exist."),
    MEMBER_NAME_EXISTS(409, "Name Already Exist."),
    MEMBER_NOT_AUTHORIZED(401, "Unauthorized member"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    QUESTION_EXISTS(409, "Question is exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
    }
