package com.jmc.stackoverflowbe.global.security.auth.handler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {
    @Getter
    @Value("${config.host}")
    private String host;

    @Getter
    @Value("${config.port}")
    private int port;
}
