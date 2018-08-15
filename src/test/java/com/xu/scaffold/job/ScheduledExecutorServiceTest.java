package com.xu.scaffold.job;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        service.scheduleAtFixedRate(new Runnable() {

            private Integer count = 0;

            @Override
            public void run() {
                count++;
                System.out.println("task count:" + count);
            }

        }, 1000,1000, TimeUnit.MILLISECONDS);
    }
}
