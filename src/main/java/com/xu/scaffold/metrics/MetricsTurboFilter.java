package com.xu.scaffold.metrics;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.lang.NonNullApi;
import io.micrometer.core.lang.NonNullFields;
import org.slf4j.Marker;

@NonNullApi
@NonNullFields
class MetricsTurboFilter extends TurboFilter {
    private final Counter errorCounter;
    private final Counter warnCounter;
    private final Counter infoCounter;
    private final Counter debugCounter;
    private final Counter traceCounter;

    MetricsTurboFilter(MeterRegistry registry, Iterable<Tag> tags) {
        this.errorCounter = Counter.builder("logback.events.x").tags(tags).tags(new String[]{"level", "error"}).description("Number of error level events that made it to the logs").register(registry);
        this.warnCounter = Counter.builder("logback.events.x").tags(tags).tags(new String[]{"level", "warn"}).description("Number of warn level events that made it to the logs").register(registry);
        this.infoCounter = Counter.builder("logback.events.x").tags(tags).tags(new String[]{"level", "info"}).description("Number of info level events that made it to the logs").register(registry);
        this.debugCounter = Counter.builder("logback.events.x").tags(tags).tags(new String[]{"level", "debug"}).description("Number of debug level events that made it to the logs").register(registry);
        this.traceCounter = Counter.builder("logback.events.x").tags(tags).tags(new String[]{"level", "trace"}).description("Number of trace level events that made it to the logs").register(registry);
    }

    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        Boolean ignored = LogbackMetricsX.ignoreMetrics.get();
        if (ignored != null && ignored) {
            return FilterReply.NEUTRAL;
        } else {
            if (level.isGreaterOrEqual(logger.getEffectiveLevel()) && format != null) {
                switch (level.toInt()) {
                    case 5000:
                        this.traceCounter.increment();
                        break;
                    case 10000:
                        this.debugCounter.increment();
                        break;
                    case 20000:
                        this.infoCounter.increment();
                        break;
                    case 30000:
                        this.warnCounter.increment();
                        break;
                    case 40000:
                        this.errorCounter.increment();
                        System.out.println("-----------"+format);
                }
            }

            return FilterReply.NEUTRAL;
        }
    }
}
