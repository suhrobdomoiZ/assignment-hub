package com.exp.assigmenthub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SubmissionResponse {
    private UUID id;

    @JsonProperty("assignment_id")
    private UUID assignmentId;

    @JsonProperty("student_email")
    private String studentEmail;

    @JsonProperty("submitted_code")
    private String submittedCode;
    private Integer score;

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

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
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
}
