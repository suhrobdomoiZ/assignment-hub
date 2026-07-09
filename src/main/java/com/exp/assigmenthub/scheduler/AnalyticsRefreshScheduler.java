package com.exp.assigmenthub.scheduler;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.*;

@Component
public class AnalyticsRefreshScheduler {
    private final JdbcTemplate jdbcTemplate;

    public AnalyticsRefreshScheduler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedDelay = 30000)
    public void refreshAnalytics() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW CONCURRENTLY assignment_analytics_mv");
    }
}