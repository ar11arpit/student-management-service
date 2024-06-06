package com.rakbank.student.controller;

import com.rakbank.student.entity.Student;
import com.rakbank.student.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

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
        Mockito.when(studentService.addStudent(Mockito.any(Student.class))).thenReturn(student);
        ResponseEntity<Student> response = studentController.addStudent(student);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("John Doe", response.getBody().getStudentName());
    }

    @Test
    public void testGetStudent() {
        Mockito.when(studentService.getStudentByStudentId("ST12345")).thenReturn(Optional.of(student));
        ResponseEntity<Student> response = studentController.getStudent("ST12345");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("John Doe", response.getBody().getStudentName());
    }
}
