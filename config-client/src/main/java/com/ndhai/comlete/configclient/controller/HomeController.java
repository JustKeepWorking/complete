package com.ndhai.comlete.configclient.controller;

import com.ndhai.comlete.configclient.config.CommonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nduyhai
 */
@RestController
//@EnableConfigurationProperties(CommonConfiguration.class)
public class HomeController {

    private CommonConfiguration commonConfiguration;

    @Autowired
    public HomeController(CommonConfiguration commonConfiguration) {
        this.commonConfiguration = commonConfiguration;
    }

    @GetMapping({"/home", "/"})
    public String getHome() {
        return this.commonConfiguration.getHello();
    }
    
}
