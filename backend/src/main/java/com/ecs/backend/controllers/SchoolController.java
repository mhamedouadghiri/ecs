package com.ecs.backend.controllers;

import com.ecs.backend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/school")
@CrossOrigin(origins = "http://localhost:3000")
public class SchoolController {

    final private SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/students/{school-id}")
    public ResponseEntity<?> getStudents(@PathVariable("school-id") Long schoolId){
        return schoolService.getStudents(schoolId);
    }
}
