package com.xu.scaffold.job;

import com.xu.scaffold.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Job2 extends AbstractJob<User> {
    @Override
    protected String doing() {
        return "user";
    }
}
