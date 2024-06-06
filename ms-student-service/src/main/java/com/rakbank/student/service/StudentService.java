package com.rakbank.student.service;

import com.rakbank.student.entity.Student;

import java.util.Optional;

public interface StudentService {
    Optional<Student> getStudentByStudentId(String studentId);

    Student addStudent(Student student) ;
}
