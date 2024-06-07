package com.rakbank.student.service;

import com.rakbank.student.entity.Student;
import com.rakbank.student.response.StudentResponse;

public interface StudentService {
    StudentResponse getStudentByStudentId(String studentId);

    StudentResponse addStudent(Student student) ;
}
