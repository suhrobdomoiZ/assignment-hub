package com.exp.assigmenthub.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    private UUID id;

    @Column(name = "assignment_id")
    private UUID assignmentId;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(columnDefinition = "TEXT")
    private String submittedCode;

    private Integer score;

    private Integer attemptsCount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(UUID assignmentId) {
        this.assignmentId = assignmentId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getSubmittedCode() {
        return submittedCode;
    }

    public void setSubmittedCode(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getAttemptsCount() {
        return attemptsCount;
    }

    public void setAttemptsCount(Integer attemptsCount) {
        this.attemptsCount = attemptsCount;
    }
}