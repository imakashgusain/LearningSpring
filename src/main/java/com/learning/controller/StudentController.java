package com.learning.controller;

import com.learning.service.StudentService;
import com.learning.entity.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @Operation(summary = "Fetches all Students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retrieved all student details"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Fetches Student detail by student id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retrieved student details"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok().body(student);
    }

    @Operation(summary = "Save student detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "student detail Saved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @Operation(summary = "update student detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "student detail updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                 @Valid @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(studentId, studentDetails);
        return ResponseEntity.ok(updatedStudent);

    }

    @Operation(summary = "delete student detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "student detail deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value = "id") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("deleted successfully");
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getUsers(@RequestParam(defaultValue = "") String searchCriteria,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        Pageable pageable;
        if (searchCriteria.isEmpty()) {
            pageable = PageRequest.of(page, size);
        } else {
            pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());
        }

        Page<Student> students =  studentService.fetchStudentsInPage(searchCriteria,pageable);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
