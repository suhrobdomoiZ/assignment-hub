CREATE TYPE DIFFICULTY AS ENUM ('easy', 'medium', 'hard');

CREATE TABLE IF NOT EXISTS assignments)(
    id UUID PRIMARY KEY DEFAULT UUIDV7(),
    title VARCHAR(128) NOT NULL CHECK (title != ''),
    description TEXT NOT NULL CHECK (description != ''),
    difficulty DIFFICULTY NOT NULL,
    max_score INTEGER NOT NULL CHECK (max_score > 0);
    image_url VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS submissions(
    id UUID PRIMARY KEY DEFAULT UUIDV7(),
    assignment_id UUID NOT NULL UNIQUE REFERENCES assignments(id) ON DELETE CASCADE,
    student_email VARCHAR(64) NOT NULL CHECK (student_email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    submitted_code VARCHAR(2048) NOT NULL CHECK (submitted_code != ''),
    score INTEGER NOT NULL CHECK (score >= 0)
);
