package com.exp.assigmenthub.dto;

import java.util.UUID;

public class AssignmentIdResponse {
    private UUID assignmentId;

    public AssignmentIdResponse(UUID assignmentId) {
        this.assignmentId = assignmentId;
    }

    public UUID getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(UUID assignmentId) {
        this.assignmentId = assignmentId;
    }
}
