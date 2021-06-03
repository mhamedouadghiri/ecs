package com.ecs.backend.services;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.model.Company;
import com.ecs.backend.model.School;
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

    private final SchoolRepository schoolRepository;
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public UserService(SchoolRepository schoolRepository, CompanyRepository companyRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.companyRepository = companyRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<?> checkUserCredentials(User user) {
        User persistedUser;
        switch (user.getUserType()) {
            case company:
                persistedUser = companyRepository.findByEmail(user.getEmail());
                break;
            case student:
                persistedUser = studentRepository.findByEmail(user.getEmail());
                break;
            case school:
                persistedUser = schoolRepository.findByEmail(user.getEmail());
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (persistedUser != null && persistedUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(persistedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> registerUser(UserDto userDto) {
        if (userDto.getUserType() == null ||
                userDto.getEmail() == null || userDto.getEmail().isEmpty() ||
                userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user;
        User savedEntity = null;
        switch (userDto.getUserType()) {
            case company:
                if (userDto.getName() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                user = new Company(null,
                        userDto.getEmail(),
                        userDto.getPassword(),  // TODO: BCrypt
                        userDto.getPhone(),
                        userDto.getName(),
                        userDto.getDescription(),
                        userDto.getCity(),
                        userDto.getCountry(),
                        userDto.getAddress());
                savedEntity = companyRepository.save((Company) user);
                break;
            case school:
                if (userDto.getName() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                user = new School(null,
                        userDto.getEmail(),
                        userDto.getPassword(),  // TODO: BCrypt
                        userDto.getPhone(),
                        userDto.getName());
                savedEntity = schoolRepository.save((School) user);
                break;
            case student:
                // do checks
                // new Student
//                savedEntity = studentRepository.saveAll((Student) user);
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (savedEntity == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(savedEntity);
    }
}
