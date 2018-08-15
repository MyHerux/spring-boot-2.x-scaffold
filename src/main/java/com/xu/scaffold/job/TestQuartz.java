package com.xu.scaffold.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestQuartz extends QuartzJobBean {

    private Integer count=0;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        count++;
        System.out.println("quartz task count:" + count);
    }
}