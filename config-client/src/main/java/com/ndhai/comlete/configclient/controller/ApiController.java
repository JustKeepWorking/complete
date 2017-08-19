package com.ndhai.comlete.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //http://cloud.spring.io/spring-cloud-static/docs/1.0.x/spring-cloud.html#_refresh_scope
public class ApiController {

    @Value("${app.custom.hello}")
    private String msg;

    @Value("${app.custom.enable}")
    private String enable;

    @GetMapping("/api")
    public String getApi() {
        return String.join(":", this.msg, this.enable);
    }
}
