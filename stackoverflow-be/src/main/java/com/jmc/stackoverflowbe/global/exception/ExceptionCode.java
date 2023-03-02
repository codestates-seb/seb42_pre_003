package com.jmc.stackoverflowbe.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_UNAUTHORIZED(401, "Member Unauthorized"),
    MEMBER_NOT_MATCH(403, "Member Not Match"),
    MEMBER_EMAIL_EXISTS(409, "Email Already Exist."),
    MEMBER_NAME_EXISTS(409, "Name Already Exist."),
    QUESTION_NOT_FOUND(404, "Question Not Found"),
    QUESTION_EXISTS(409, "Question exists"),
    ANSWER_NOT_FOUND(404, "Answer Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
    }
