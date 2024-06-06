package com.rakbank.student.service;

import com.rakbank.student.entity.Student;
import com.rakbank.student.repository.StudentRepository;
import com.rakbank.student.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setStudentName("John Doe");
        student.setStudentId("ST12345");
        student.setGrade("10");
        student.setMobileNumber("1234567890");
        student.setSchoolName("Springfield High");
    }

    @Test
    public void testAddStudent() {
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Student savedStudent = studentService.addStudent(student);
        Assertions.assertNotNull(savedStudent);
        Assertions.assertEquals("John Doe", savedStudent.getStudentName());
    }

    @Test
    public void testGetStudentByStudentId() {
        Mockito.when(studentRepository.findByStudentId("ST12345")).thenReturn(Optional.of(student));
        Optional<Student> foundStudent = studentService.getStudentByStudentId("ST12345");
        Assertions.assertTrue(foundStudent.isPresent());
        Assertions.assertEquals("John Doe", foundStudent.get().getStudentName());
    }
}

