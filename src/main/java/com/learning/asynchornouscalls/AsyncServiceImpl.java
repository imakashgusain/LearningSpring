package com.learning.asynchornouscalls;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
        @Async
        @Override
        public void asyncMethod() throws InterruptedException {
                Thread.sleep(3000);
                log.info("Calling other service..");
                // this will throw exception
                int value =5/0;
                log.info("Thread: " +
                        Thread.currentThread().getName());
        }


}