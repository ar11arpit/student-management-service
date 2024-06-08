package com.rakbank.student.service;

import com.rakbank.student.dto.StudentRequest;
import com.rakbank.student.entity.Student;
import com.rakbank.student.dto.StudentResponse;
import com.rakbank.student.exceptions.CustomException;

import java.util.List;

public interface StudentService {
    StudentResponse getStudentByStudentId(String studentId) throws CustomException;

    StudentResponse addStudent(StudentRequest student) ;

    List<StudentResponse> getStudentDetials();
}
