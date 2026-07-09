CREATE INDEX IF NOT EXISTS idx_submissions_assignment_id ON submissions(assignment_id);
CREATE INDEX IF NOT EXISTS idx_submissions_score ON submissions(score);
CREATE INDEX IF NOT EXISTS idx_submissions_assignment_student ON submissions(assignment_id, student_email);

CREATE MATERIALIZED VIEW IF NOT EXISTS assignment_analytics_mv AS
WITH student_stats AS (
    SELECT
        s.assignment_id,
        s.student_email,
        COUNT(s.id) AS total_attempts,
        BOOL_OR(s.score >= a.max_score) AS has_success
    FROM submissions s
    JOIN assignments a ON s.assignment_id = a.id
    GROUP BY s.assignment_id, s.student_email
),
assignment_attempts AS (
    SELECT
        assignment_id,
        AVG(total_attempts) FILTER (WHERE has_success) AS avg_attempts_to_success
    FROM student_stats
    GROUP BY assignment_id
)
SELECT
    a.id AS assignment_id,
    a.title,
    a.difficulty::text AS difficulty,
    COUNT(s.id)::int AS total_submissions,
    ROUND(AVG(s.score), 2)::float AS avg_score,
    ROUND(ast.avg_attempts_to_success, 2)::float AS avg_attempts,
    ROUND(COUNT(s.id) FILTER (WHERE s.score >= a.max_score) * 100.0 / NULLIF(COUNT(s.id), 0), 2)::float AS success_rate
FROM assignments a
         LEFT JOIN submissions s ON s.assignment_id = a.id
         LEFT JOIN assignment_attempts ast ON a.id = ast.assignment_id
GROUP BY a.id, a.title, a.difficulty, ast.avg_attempts_to_success;

CREATE UNIQUE INDEX IF NOT EXISTS idx_analytics_mv_id ON assignment_analytics_mv(assignment_id);
