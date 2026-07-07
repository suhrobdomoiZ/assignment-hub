package com.exp.assigmenthub.service;

import com.exp.assigmenthub.dto.AssignmentAnalytics;
import com.exp.assigmenthub.model.Difficulty;
import com.exp.assigmenthub.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    private final AssignmentRepository assignmentRepository;

    public AnalyticsService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Transactional(readOnly = true)
    public List<AssignmentAnalytics> getSummary() {
        List<Object[]> rawData = assignmentRepository.getAnalyticsSummaryRaw();

        return rawData.stream().map(row -> {
            AssignmentAnalytics analytics = new AssignmentAnalytics();

            analytics.setAssignmentId(row[0] != null ? UUID.fromString((String) row[0]) : null);
            analytics.setTitle((String) row[1]);
            analytics.setDifficulty(row[2] != null ? Difficulty.valueOf((String) row[2]) : null);
            analytics.setTotalSubmissions(row[3] != null ? ((Number) row[3]).intValue() : 0);
            analytics.setAverageScore(row[4] != null ? ((Number) row[4]).floatValue() : 0.0f);
            analytics.setAverageAttempts(row[5] != null ? ((Number) row[5]).floatValue() : 0.0f);
            analytics.setSuccessRatePercentage(row[6] != null ? ((Number) row[6]).floatValue() : 0.0f);

            return analytics;
        }).collect(Collectors.toList());
    }
}