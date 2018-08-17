package com.xu.scaffold.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JobMetrics implements MeterBinder {


    public Counter job1Counter;

    public Counter job2Counter;

    public Map<String, Double> map;

    JobMetrics() {
        map = new HashMap<>();
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.job1Counter = Counter.builder("my_job")
                .tags(new String[]{"name", "job1"})
                .description("Job 1 execute count").register(meterRegistry);

        this.job2Counter = Counter.builder("my_job")
                .tags(new String[]{"name", "job2"})
                .description("Job 2 execute count").register(meterRegistry);

        Gauge.builder("my_job_gauge", map, x -> x.get("x"))
                .tags("name", "job1")
                .description("")
                .register(meterRegistry);

    }

}
