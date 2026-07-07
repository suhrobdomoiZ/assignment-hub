package com.exp.assigmenthub.service;

import com.exp.assigmenthub.dto.SubmissionCreate;
import com.exp.assigmenthub.model.Submission;
import com.exp.assigmenthub.model.Assignment;
import com.exp.assigmenthub.repository.AssignmentRepository;
import com.exp.assigmenthub.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;

    SubmissionService(SubmissionRepository submissionRepository, AssignmentRepository assignmentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public Submission create(SubmissionCreate dto) {
        Assignment assignment;
        assignment = assignmentRepository.findById(dto.getAssignmentId()).orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setId(UUID.randomUUID());
        submission.setStudentEmail(dto.getStudentEmail());
        submission.setSubmittedCode(dto.getSubmittedCode());
        submission.setScore(dto.getScore());
        return submissionRepository.save(submission);
    }

    public Submission getById(UUID id) {
        return submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("submission not found"));
    }

    public Submission update(UUID submissionId, SubmissionCreate dto) {
        Submission submission = getById(submissionId);
        submission.setSubmittedCode(dto.getSubmittedCode());
        submission.setScore(dto.getScore());
        return submissionRepository.save(submission);
    }

    public void delete(UUID id) {
        submissionRepository.deleteById(id);
    }
}
