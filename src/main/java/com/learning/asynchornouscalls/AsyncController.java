package com.learning.asynchornouscalls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@EnableAsync
public class AsyncController {
    @Autowired
    AsyncService asyncService;
    @GetMapping("/async")
    public String asyncCallerMethod() throws InterruptedException {
        long start = System.currentTimeMillis();
        asyncService.asyncMethod();
        return "task completes in :" + (start -   System.currentTimeMillis()) + "milliseconds";

    }
}
