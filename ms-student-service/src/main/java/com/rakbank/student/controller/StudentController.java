package com.rakbank.student.controller;

import com.rakbank.student.entity.Student;
import com.rakbank.student.response.StudentResponse;
import com.rakbank.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;
@RestController
@RequestMapping("/api/student")
@Tag(name = "Student API", description = "API for managing students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Add a new student", description = "Add a new student to the database")
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody Student student) {
        StudentResponse savedStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Get student by ID", description = "Retrieve student details by student ID")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable String studentId) {
        StudentResponse student = studentService.getStudentByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping
    @Operation(summary = "Get All students", description = "Retrieve All student details")
    public ResponseEntity<List<StudentResponse>> getStudentDetails() {
        List<StudentResponse> student = studentService.getStudentDetials();
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
