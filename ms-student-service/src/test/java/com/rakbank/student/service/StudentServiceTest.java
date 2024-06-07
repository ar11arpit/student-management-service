package com.rakbank.student.service;

import com.rakbank.student.entity.Student;
import com.rakbank.student.repository.StudentRepository;
import com.rakbank.student.response.StudentResponse;
import com.rakbank.student.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void addStudent_NewStudent_Success() {
        Student student = new Student();
        student.setStudentId("S12345");
        student.setStudentName("John Doe");
        when(studentRepository.save(student)).thenReturn(student);

        StudentResponse savedStudent = studentService.addStudent(student);

        assertNotNull(savedStudent);
        assertEquals("John Doe", savedStudent.getStudentName());

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void getStudentByStudentId_ExistingStudent_Success() {
        Student existingStudent = new Student();
        existingStudent.setStudentName("John Doe");

        when(studentRepository.findByStudentId("S12345")).thenReturn(Optional.of(existingStudent));

        StudentResponse studentOptional = studentService.getStudentByStudentId("S12345");
        studentOptional.setStudentId(null);
        assertEquals(studentService.mapToResponse(existingStudent).getStudentName(), studentOptional.getStudentName());

        verify(studentRepository, times(1)).findByStudentId("S12345");
    }

    @Test
    void getStudentByStudentId_NonExistingStudent_Failure() {
        when(studentRepository.findByStudentId("S12345")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> studentService.getStudentByStudentId("S12345"));
        assertEquals("student not found", exception.getMessage());

        verify(studentRepository, times(1)).findByStudentId("S12345");
    }
}
