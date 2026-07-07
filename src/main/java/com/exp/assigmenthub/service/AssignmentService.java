package com.exp.assigmenthub.service;

import com.exp.assigmenthub.dto.AssignmentCreate;
import com.exp.assigmenthub.dto.AssignmentIdResponse;
import com.exp.assigmenthub.model.Assignment;
import com.exp.assigmenthub.model.Submission;
import com.exp.assigmenthub.repository.AssignmentRepository;
import com.exp.assigmenthub.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final String uploadDir = "uploads/";

    public AssignmentService(AssignmentRepository assignmentRepository, SubmissionRepository submissionRepository) {
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
    }

    public AssignmentIdResponse createAssignment(AssignmentCreate dto) {
        Assignment assignment = new Assignment();
        assignment.setId(UUID.randomUUID()); // или пусть БД генерирует через @GeneratedValue
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDifficulty(dto.getDifficulty());
        assignment.setMaxScore(dto.getMaxScore());
        assignmentRepository.save(assignment);

        return new AssignmentIdResponse(assignment.getId());
    }

    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    public Assignment getById(UUID id) {
        return assignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Assignment update(UUID id, Assignment dto) {
        Assignment assignment = getById(id);
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDifficulty(dto.getDifficulty());
        assignment.setMaxScore(dto.getMaxScore());
        return assignmentRepository.save(assignment);
    }

    public void deleteById(UUID id) {
        Assignment assignment = getById(id);
        assignmentRepository.delete(assignment);
    }

    public void  uploadImage(UUID id, byte[] imageBytes) throws IOException {
        Assignment assignment = getById(id);
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, id + ".jpg");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageBytes);
        }
        assignment.setImageURL("/api/v1/assignment/" + id + "/image");
        assignmentRepository.save(assignment);
    }

    public byte[] getImage(UUID id) throws IOException {
        File file = new File(uploadDir + id + ".jpg");
        if (!file.exists()) {
            throw new RuntimeException("Image not found");
        }
        return Files.readAllBytes(file.toPath());
    }

    public List<Submission> getSubmissionsByAssignmentId(UUID id) {
        return submissionRepository.findByAssignmentId(id);
    }


}
