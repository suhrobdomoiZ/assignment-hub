package com.exp.assigmenthub.repository;

import com.exp.assigmenthub.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {

    @Query(value = """
        WITH student_stats AS (
            SELECT 
                s.assignment_id,
                s.student_email,
                COUNT(s.id) AS total_attempts,
                BOOL_OR(s.score >= a.max_score) AS has_success
            FROM 
                submissions s
                JOIN assignments a ON s.assignment_id = a.id
            GROUP BY 
                s.assignment_id, s.student_email
        ),
        assignment_attempts AS (
            SELECT 
                assignment_id,
                AVG(total_attempts) FILTER (WHERE has_success) AS avg_attempts_to_success
            FROM 
                student_stats
            GROUP BY 
                assignment_id
        )
        
        SELECT
            CAST(a.id AS VARCHAR) AS assignment_id,
            a.title,
            CAST(a.difficulty AS VARCHAR) AS difficulty,
            COUNT(s.id) AS total_submissions,
            ROUND(AVG(s.score), 2) AS avg_score,
            ROUND(ast.avg_attempts_to_success, 2) AS avg_attempts,
            ROUND(COUNT(s.id) FILTER (WHERE s.score >= a.max_score) * 100.0 / NULLIF(COUNT(s.id), 0), 2) AS success_rate
        FROM
            assignments a
            LEFT JOIN submissions s ON s.assignment_id = a.id
            LEFT JOIN assignment_attempts ast ON a.id = ast.assignment_id
        GROUP BY
            a.id, 
            a.title, 
            a.difficulty, 
            ast.avg_attempts_to_success
        """, nativeQuery = true)
    List<Object[]> getAnalyticsSummaryRaw();
}