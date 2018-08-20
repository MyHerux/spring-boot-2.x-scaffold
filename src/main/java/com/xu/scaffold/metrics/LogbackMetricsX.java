package com.xu.scaffold.metrics;

import ch.qos.logback.classic.LoggerContext;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LogbackMetricsX implements MeterBinder, AutoCloseable {
    static ThreadLocal<Boolean> ignoreMetrics = new ThreadLocal();
    private final Iterable<Tag> tags;
    private final LoggerContext loggerContext;
    private final Map<MeterRegistry, MetricsTurboFilter> metricsTurboFilters;

    public LogbackMetricsX() {
        this(Collections.emptyList());
    }

    public LogbackMetricsX(Iterable<Tag> tags) {
        this(tags, (LoggerContext) LoggerFactory.getILoggerFactory());
    }

    public LogbackMetricsX(Iterable<Tag> tags, LoggerContext context) {
        this.metricsTurboFilters = new ConcurrentHashMap();
        this.tags = tags;
        this.loggerContext = context;
    }

    @Override
    public void bindTo(MeterRegistry registry) {
        MetricsTurboFilter filter = new MetricsTurboFilter(registry, this.tags);
        this.metricsTurboFilters.put(registry, filter);
        this.loggerContext.addTurboFilter(filter);
    }

    public static void ignoreMetrics(Runnable r) {
        ignoreMetrics.set(true);
        r.run();
        ignoreMetrics.remove();
    }

    @Override
    public void close() {
        Iterator var1 = this.metricsTurboFilters.values().iterator();

        while (var1.hasNext()) {
            MetricsTurboFilter metricsTurboFilter = (MetricsTurboFilter) var1.next();
            this.loggerContext.getTurboFilterList().remove(metricsTurboFilter);
        }

    }
}
