package com.exp.assigmenthub.controller;

import com.exp.assigmenthub.dto.AssignmentAnalytics;
import com.exp.assigmenthub.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public ResponseEntity<List<AssignmentAnalytics>> getSummary() {
        List<AssignmentAnalytics> summary = analyticsService.getSummary();
        return ResponseEntity.ok(summary);
    }
}