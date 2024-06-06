package com.rakbank.student.controller;

import com.rakbank.student.entity.Student;
import com.rakbank.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/student")
@Tag(name = "Student API", description = "API for managing students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Add a new student", description = "Add a new student to the database")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Get student by ID", description = "Retrieve student details by student ID")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId) {
        Optional<Student> student = studentService.getStudentByStudentId(studentId);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}