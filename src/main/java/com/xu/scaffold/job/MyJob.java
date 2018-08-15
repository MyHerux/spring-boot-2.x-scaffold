package com.xu.scaffold.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyJob {

    private Integer count = 0;

    @Async("main")
    @Scheduled(fixedDelay = 5000)
    public void doSomething() {
        count++;
        System.out.println("task count:" + count);
    }

    @Async
    @Scheduled(fixedDelay = 1000)
    public void doSomethingOther() {
        count++;
        System.out.println("task count:" + count);
    }
}
