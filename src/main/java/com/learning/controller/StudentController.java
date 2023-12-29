package com.learning.controller;

import com.learning.exception.ResourceNotFoundException;
import com.learning.service.StudentService;
import com.learning.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok().body(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                 @RequestBody Student studentDetails)
            throws ResourceNotFoundException {
        Student updatedStudent = studentService.updateStudent(studentId, studentDetails);
        return ResponseEntity.ok(updatedStudent);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("deleted successfully");
    }
}
