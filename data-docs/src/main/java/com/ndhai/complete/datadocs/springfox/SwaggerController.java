package com.ndhai.complete.datadocs.springfox;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "Swagger", description = "Swagger custom controller ", tags = "custom")
public class SwaggerController {

    @GetMapping("/swagger/custom")
    @ApiOperation(value = "Get custom", notes = "This is custom api", nickname = "getCustomNickName")
    public String getCustom() {
        return "Get Custom";
    }

    @PostMapping("/swagger/custom")
    public String postCustom() {
        return "Post Custom";
    }

}
