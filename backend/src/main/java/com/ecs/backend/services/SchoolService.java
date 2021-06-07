package com.ecs.backend.services;

import com.ecs.backend.model.User;
import com.ecs.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final StudentRepository studentRepository;

    @Autowired
    public SchoolService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<?> getStudents(Long schoolId) {
        List<User> students = studentRepository.findAllBySchoolId(schoolId);
        if (students == null || students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(students);
    }
}
