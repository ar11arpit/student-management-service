package com.rakbank.student.service;
import java.util.List;
import com.rakbank.student.entity.Student;
import com.rakbank.student.response.StudentResponse;

public interface StudentService {
    StudentResponse getStudentByStudentId(String studentId);

    StudentResponse addStudent(Student student) ;

    List<StudentResponse> getStudentDetials();
}
