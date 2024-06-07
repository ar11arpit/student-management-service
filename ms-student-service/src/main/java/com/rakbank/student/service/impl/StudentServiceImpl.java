package com.rakbank.student.service.impl;

import com.rakbank.student.entity.Student;
import com.rakbank.student.repository.StudentRepository;
import com.rakbank.student.response.StudentResponse;
import com.rakbank.student.service.StudentService;
import com.rakbank.student.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentResponse addStudent(Student student) {
        return mapToResponse(studentRepository.save(student));
    }

    public StudentResponse getStudentByStudentId(String studentId) {
        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);
        if(studentOptional.isEmpty()){
            throw new RuntimeException("student not found");
        }
        return mapToResponse(studentOptional.get());
    }

     @Override
    public List<StudentResponse> getStudentDetials() {
        return studentRepository.findAll().stream().map(student->mapToResponse(student)).collect(Collectors.toList());
    }
    
    public StudentResponse mapToResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(Utils.generateStudentId());
        studentResponse.setId(student.getId());
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setGrade(student.getGrade());
        studentResponse.setSchoolName(student.getSchoolName());
        studentResponse.setMobileNumber(student.getMobileNumber());
        studentResponse.setYearlyFee(student.getYearlyFee());

        return studentResponse;
    }
}
