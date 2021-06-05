package com.ecs.backend.controllers;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/educations/{student-id}")
    public ResponseEntity<?> getEducations(@PathVariable("student-id") Long studentId) {
        return studentService.getEducations(studentId);
    }

    @GetMapping("/experiences/{student-id}")
    public ResponseEntity<?> getExperiences(@PathVariable("student-id") Long studentId){
        return studentService.getExperiences(studentId);
    }

    @PostMapping("/save/education")
    public ResponseEntity<?> saveEducatin(@RequestBody UserDto user){
        return studentService.saveEducation(user);
    }

    @PostMapping("/save/experience")
    public ResponseEntity<?> saveExperience(@RequestBody UserDto user){
        return studentService.saveExperience(user);
    }
}
