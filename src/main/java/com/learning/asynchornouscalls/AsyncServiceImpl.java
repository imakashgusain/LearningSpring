package com.learning.asynchornouscalls;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class AsyncServiceImpl implements AsyncService {
        @Async
        @Override
        public void asyncMethod() throws InterruptedException {
                Thread.sleep(3000);
                System.out.println("Calling other service..");
                // this will throw exception
                System.out.println(5/0);
                System.out.println("Thread: " +
                        Thread.currentThread().getName());
        }


}