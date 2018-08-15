package com.xu.scaffold.job;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {

            private Integer count = 0;

            @Override
            public void run() {
                count++;
                System.out.println("task count:"+count);
            }
        };

        Timer timer=new Timer();
        timer.schedule(timerTask,1000,1000);
    }
}
