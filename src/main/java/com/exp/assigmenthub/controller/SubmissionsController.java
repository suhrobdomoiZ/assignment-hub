package com.exp.assigmenthub.controller;

import com.exp.assigmenthub.dto.SubmissionCreate;
import com.exp.assigmenthub.dto.SubmissionResponse;
import com.exp.assigmenthub.service.SubmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/submissions")
public class SubmissionsController {
    private final SubmissionService submissionService;

    public SubmissionsController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> create(@RequestBody SubmissionCreate dto) {
        SubmissionResponse created = submissionService.create(dto);
        return new ResponseEntity<>(Map.of("id", created.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/{submission_id}")
    public ResponseEntity<SubmissionResponse> getById(@PathVariable("submission_id") UUID id) {
        return ResponseEntity.ok(submissionService.getById(id));
    }

    @PutMapping("/{submission_id}")
    public ResponseEntity<SubmissionResponse> update(@PathVariable("submission_id") UUID id, @RequestBody SubmissionCreate dto) {
        return ResponseEntity.ok(submissionService.update(id, dto));
    }

    @DeleteMapping("/{submission_id}")
    public ResponseEntity<Void> delete(@PathVariable("submission_id") UUID id) {
        submissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}