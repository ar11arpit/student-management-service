package com.rakbank.student.service.impl;

import com.rakbank.student.dto.StudentRequest;
import com.rakbank.student.entity.Student;
import com.rakbank.student.exceptions.CustomException;
import com.rakbank.student.exceptions.ErrorType;
import com.rakbank.student.repository.StudentRepository;
import com.rakbank.student.dto.StudentResponse;
import com.rakbank.student.service.StudentService;
import com.rakbank.student.utils.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Transactional
    public StudentResponse addStudent(StudentRequest student) {
        Student studentEntity = mapToEntity(student);
        return mapToResponse(studentRepository.save(studentEntity));
    }

    private Student mapToEntity(StudentRequest student) {
        Student studentEntity = new Student();
        studentEntity.setStudentName(student.getStudentName());
        studentEntity.setGrade(student.getGrade());
        studentEntity.setStudentId(Utils.generateStudentId());
        studentEntity.setMobileNumber(student.getMobileNumber());
        studentEntity.setYearlyFee(student.getYearlyFee());
        studentEntity.setSchoolName(student.getSchoolName());
        return studentEntity;
    }

    @Override
    public List<StudentResponse> getStudentDetials() {
        return studentRepository.findAll().stream().map(student->mapToResponse(student)).collect(Collectors.toList());
    }

    public StudentResponse getStudentByStudentId(String studentId) throws CustomException {
        Optional<Student> studentOptional = studentRepository.findByStudentId(studentId);
        if(studentOptional.isEmpty()){
            throw new CustomException("student not found", ErrorType.FUNCTIONAL);
        }
        return mapToResponse(studentOptional.get());
    }
    public StudentResponse mapToResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setGrade(student.getGrade());
        studentResponse.setSchoolName(student.getSchoolName());
        studentResponse.setMobileNumber(student.getMobileNumber());
        studentResponse.setYearlyFee(student.getYearlyFee());

        return studentResponse;
    }
}
