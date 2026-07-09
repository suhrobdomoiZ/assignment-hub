package com.exp.assigmenthub.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.UUID;
@Entity
@Immutable
@Table(name = "assignment_analytics_mv")
public class Analytics {
    @Id
    @Column(name = "assignment_id")
    private UUID assignmentId;
    private String title;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Column(name = "total_submissions")
    private Integer totalSubmissions;
    @Column(name = "avg_score")
    private Float avgScore;
    @Column(name = "avg_attempts")
    private Float avgAttempts;
    @Column(name = "success_rate")
    private Float successRate;

    public UUID getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(UUID assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Float getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Float successRate) {
        this.successRate = successRate;
    }

    public Float getAvgAttempts() {
        return avgAttempts;
    }

    public void setAvgAttempts(Float avgAttempts) {
        this.avgAttempts = avgAttempts;
    }

    public Float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Float avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getTotalSubmissions() {
        return totalSubmissions;
    }

    public void setTotalSubmissions(Integer totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}