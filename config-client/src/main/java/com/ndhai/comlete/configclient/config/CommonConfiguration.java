package com.ndhai.comlete.configclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.custom")
public class CommonConfiguration {
    private String hello;

    private boolean enable;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }
}
