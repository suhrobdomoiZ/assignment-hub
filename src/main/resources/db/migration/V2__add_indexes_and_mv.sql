CREATE INDEX IF NOT EXISTS idx_submissions_assignment_student_score
    ON submissions (assignment_id, student_email) INCLUDE (score);