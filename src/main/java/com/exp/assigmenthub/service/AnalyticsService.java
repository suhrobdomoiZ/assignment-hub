package com.exp.assigmenthub.service;

import com.exp.assigmenthub.dto.AssignmentAnalytics;
import com.exp.assigmenthub.model.Analytics;
import com.exp.assigmenthub.repository.AnalyticsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    @Transactional(readOnly = true)
    public List<AssignmentAnalytics> getSummary() {
        return analyticsRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private AssignmentAnalytics toDto(Analytics entity) {
        AssignmentAnalytics dto = new AssignmentAnalytics();
        dto.setAssignmentId(entity.getAssignmentId());
        dto.setTitle(entity.getTitle());
        dto.setDifficulty(entity.getDifficulty());
        dto.setTotalSubmissions(entity.getTotalSubmissions());
        dto.setAverageScore(entity.getAvgScore());
        dto.setAverageAttempts(entity.getAvgAttempts());
        dto.setSuccessRatePercentage(entity.getSuccessRate());
        return dto;
    }
}
