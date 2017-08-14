package com.ndhai.complete.security.ldap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapController {
    @GetMapping("/")
    public String getIndex() {
        return "Welcome to the home page!";
    }
}
