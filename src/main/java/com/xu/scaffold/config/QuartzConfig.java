package com.xu.scaffold.config;

import com.xu.scaffold.job.TestQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail teatQuartzDetail() {
        return JobBuilder
                .newJob(TestQuartz.class)
                .withIdentity("job1", "group1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(20)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
                .withIdentity("trigger1", "group1")
                .withSchedule(scheduleBuilder)
                .build();
    }
}