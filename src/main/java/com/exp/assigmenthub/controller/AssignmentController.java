package com.exp.assigmenthub.controller;

import com.exp.assigmenthub.dto.AssignmentCreate;
import com.exp.assigmenthub.dto.AssignmentIdResponse;
import com.exp.assigmenthub.dto.SubmissionResponse;
import com.exp.assigmenthub.model.Assignment;
import com.exp.assigmenthub.model.Submission;
import com.exp.assigmenthub.service.AssignmentService;
import com.exp.assigmenthub.service.SubmissionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final SubmissionService submissionService;

    public AssignmentController(AssignmentService assignmentService, SubmissionService submissionService) {
        this.assignmentService = assignmentService;
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<AssignmentIdResponse> create(@RequestBody AssignmentCreate dto) {
        return new ResponseEntity<>(assignmentService.createAssignment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Assignment>> getAll() {
        return ResponseEntity.ok(assignmentService.getAll());
    }

    @GetMapping("/{assignment_id}")
    public ResponseEntity<Assignment> getById(@PathVariable("assignment_id") UUID id) {
        return ResponseEntity.ok(assignmentService.getById(id));
    }

    @PutMapping("/{assignment_id}")
    public ResponseEntity<Assignment> update(@PathVariable("assignment_id") UUID id, @RequestBody Assignment dto) {
        return ResponseEntity.ok(assignmentService.update(id, dto));
    }

    @DeleteMapping("/{assignment_id}")
    public ResponseEntity<Void> delete(@PathVariable("assignment_id") UUID id) {
        assignmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{assignment_id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadImage(@PathVariable("assignment_id") UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        assignmentService.uploadImage(id, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{assignment_id}/image")
    public void getImage(@PathVariable("assignment_id") UUID id, HttpServletResponse response) throws IOException {
        byte[] image = assignmentService.getImage(id);

        response.setContentType("image/png");

        response.getOutputStream().write(image);
        response.getOutputStream().flush();
    }

    @GetMapping("/{assignment_id}/submissions")
    public ResponseEntity<List<SubmissionResponse>> getSubmissionsByAssignmentId(
            @PathVariable("assignment_id") UUID assignmentId) {

        List<SubmissionResponse> submissions = submissionService.getByAssignmentId(assignmentId);
        return ResponseEntity.ok(submissions);
    }
}
