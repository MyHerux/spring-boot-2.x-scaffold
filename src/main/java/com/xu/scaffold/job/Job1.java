package com.xu.scaffold.job;

import com.xu.scaffold.entity.Info;
import org.springframework.stereotype.Component;

@Component
public class Job1 extends AbstractJob<Info> {
    @Override
    protected String doing() {
        return "info";
    }
}
