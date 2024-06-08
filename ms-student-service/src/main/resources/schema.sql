CREATE TABLE Student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(255) NOT NULL,
    student_id VARCHAR(255) NOT NULL,
    grade VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(255) NOT NULL,
    school_name VARCHAR(255) NOT NULL,
    yearly_fee DECIMAL(19, 2) NOT NULL
);