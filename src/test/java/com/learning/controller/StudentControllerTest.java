//package com.learning.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.learning.entity.Student;
//import com.learning.service.StudentService;
//import net.bytebuddy.asm.Advice;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//@ContextConfiguration(classes=StudentController.class)
//class StudentControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private StudentService studentService;
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    void getAllStudents() throws Exception {
//        List<Student> students = new ArrayList<>();
//        Student student = new Student();
//        student.setId(1L);
//        student.setFirstName("Mike");
//        students.add(student);
//        Mockito.when(studentService.getAllStudents()).thenReturn(students);
//        mockMvc.perform(get("/student")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Mike")));
//    }
//
//
//    @Test
//    void getStudentById() {
//    }
//
//    @Test
//    void createStudent() throws JsonProcessingException {
//        Student student = new Student();
//        student.setId(1L);
//        student.setFirstName("Mike");
//        Mockito.when(studentService.createStudent(Mockito.any())).thenReturn(student);
//        String json = mapper.writeValueAsString(student);
//        mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON).body(json).accept(MediaType.APPLICATION_JSON).andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
//                .andExpect(jsonPath("$.name", Matchers.equalTo("Mike")));
//
//    }
//
//    @Test
//    void updateStudent() throws JsonProcessingException {
//        Student student = new Student();
//        student.setFirstName("Rohan");
//        Mockito.when(studentService.updateStudent(Mockito.any(), Mockito.any())).thenReturn(student);
//        String json = mapper.writeValueAsString(student);
//        mockMvc.perform(put("/student").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
//                .andExpect(jsonPath("$.name", Matchers.equalTo("John")));
//    }
//    }
//
//    @Test
//    void deleteStudent() throws Exception {
//        Mockito.doNothing().when(studentService).deleteStudent(Mockito.any());
//        MvcResult requestResult = mockMvc.perform(delete("/student").param("id", "1"))
//                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
//        String result = requestResult.getResponse().getContentAsString();
//        assertEquals(result, "Student is deleted");
//    }
//}