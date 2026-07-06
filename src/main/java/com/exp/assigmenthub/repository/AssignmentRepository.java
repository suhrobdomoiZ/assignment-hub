package com.exp.assigmenthub.repository;

import com.exp.assigmenthub.model.Assignment;
import com.exp.assigmenthub.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {

}
