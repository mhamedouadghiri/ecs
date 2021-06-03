package com.ecs.backend.services;

import com.ecs.backend.model.User;
import com.ecs.backend.repositories.CompanyRepository;
import com.ecs.backend.repositories.SchoolRepository;
import com.ecs.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final private SchoolRepository schoolRepository;
    final private CompanyRepository companyRepository;
    final private StudentRepository studentRepository;

    @Autowired
    public UserService(SchoolRepository schoolRepository, CompanyRepository companyRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.companyRepository = companyRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<?> checkUserCredentials(User user) {
        User Checker;
        switch (user.getUserType()){
            case company:
                Checker = companyRepository.getByEmail(user.getEmail());
                break;
            case student:
                Checker = studentRepository.getByEmail(user.getEmail());
                break;
            case school:
                Checker = schoolRepository.getByEmail(user.getEmail());
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (Checker != null && Checker.getPassword().equals(user.getPassword())){
            return ResponseEntity.ok(Checker);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
