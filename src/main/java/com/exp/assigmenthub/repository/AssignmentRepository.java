package com.exp.assigmenthub.repository;

import com.exp.assigmenthub.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {

}
