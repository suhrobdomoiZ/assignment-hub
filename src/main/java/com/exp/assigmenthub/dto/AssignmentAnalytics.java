package com.exp.assigmenthub.dto;

import com.exp.assigmenthub.model.Difficulty;

import java.util.UUID;

public class AssignmentAnalytics {
    private UUID assignmentId;
    private String title;
    private Difficulty difficulty;
    private Integer totalSubmissions;
    private Float averageScore;
    private Float averageAttempts;
    private Float successRatePercentage;

    public UUID getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(UUID assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Float getAverageAttempts() {
        return averageAttempts;
    }

    public void setAverageAttempts(Float averageAttempts) {
        this.averageAttempts = averageAttempts;
    }

    public Float getSuccessRatePercentage() {
        return successRatePercentage;
    }

    public void setSuccessRatePercentage(Float successRatePercentage) {
        this.successRatePercentage = successRatePercentage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getTotalSubmissions() {
        return totalSubmissions;
    }

    public void setTotalSubmissions(Integer totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }
}
