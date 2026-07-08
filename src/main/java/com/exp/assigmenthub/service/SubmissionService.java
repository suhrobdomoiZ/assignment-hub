package com.exp.assigmenthub.service;

import com.exp.assigmenthub.dto.SubmissionCreate;
import com.exp.assigmenthub.dto.SubmissionResponse;
import com.exp.assigmenthub.model.Submission;
import com.exp.assigmenthub.model.Assignment;
import com.exp.assigmenthub.repository.AssignmentRepository;
import com.exp.assigmenthub.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;

    public SubmissionService(SubmissionRepository submissionRepository, AssignmentRepository assignmentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Transactional
    public SubmissionResponse create(SubmissionCreate dto) {
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentId())
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setId(UUID.randomUUID());
        submission.setStudentEmail(dto.getStudentEmail());
        submission.setSubmittedCode(dto.getSubmittedCode());
        submission.setScore(dto.getScore());

        Submission saved = submissionRepository.save(submission);
        return mapToResponse(saved);
    }

    private Submission getEntityById(UUID id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("submission not found"));
    }

    @Transactional(readOnly = true)
    public SubmissionResponse getById(UUID id) {
        Submission submission = getEntityById(id);
        return mapToResponse(submission);
    }

    @Transactional
    public SubmissionResponse update(UUID submissionId, SubmissionCreate dto) {
        Submission submission = getEntityById(submissionId);
        submission.setSubmittedCode(dto.getSubmittedCode());
        submission.setScore(dto.getScore());

        Submission updated = submissionRepository.save(submission);
        return mapToResponse(updated);
    }

    @Transactional
    public void delete(UUID id) {
        submissionRepository.deleteById(id);
    }

    private SubmissionResponse mapToResponse(Submission submission) {
        SubmissionResponse response = new SubmissionResponse();
        response.setId(submission.getId());
        response.setAssignmentId(submission.getAssignment().getId());
        response.setStudentEmail(submission.getStudentEmail());
        response.setSubmittedCode(submission.getSubmittedCode());
        response.setScore(submission.getScore());
        return response;
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponse> getByAssignmentId(UUID assignmentId) {
        List<Submission> submissions = submissionRepository.findByAssignmentId(assignmentId);

        return submissions.stream()
                .map(this::mapToResponse)
                .toList();
    }
}