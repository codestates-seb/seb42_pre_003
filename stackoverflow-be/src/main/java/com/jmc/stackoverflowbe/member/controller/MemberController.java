package com.jmc.stackoverflowbe.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    @PostMapping
    public ResponseEntity postMember() {
        return null;
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember() {
        return null;
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember() {
        return null;
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember() {
        return null;
    }
}
