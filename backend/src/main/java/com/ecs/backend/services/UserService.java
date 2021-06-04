package com.ecs.backend.services;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.model.Company;
import com.ecs.backend.model.School;
import com.ecs.backend.model.Student;
import com.ecs.backend.model.User;
import com.ecs.backend.repositories.CompanyRepository;
import com.ecs.backend.repositories.SchoolRepository;
import com.ecs.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final SchoolRepository schoolRepository;
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(SchoolRepository schoolRepository, CompanyRepository companyRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.schoolRepository = schoolRepository;
        this.companyRepository = companyRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (persistedUser != null && passwordEncoder.matches(user.getPassword(), persistedUser.getPassword())) {
            persistedUser.setPassword("you can't see the password");
            return ResponseEntity.ok(persistedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> registerUser(UserDto userDto) {
        if (userDto.getUserType() == null ||
                userDto.getEmail() == null || userDto.getEmail().isEmpty() ||
                userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user;
        User savedEntity;
        switch (userDto.getUserType()) {
            case company:
                if (userDto.getName() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                user = new Company(null,
                        userDto.getEmail(),
                        passwordEncoder.encode(userDto.getPassword()),
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
                        passwordEncoder.encode(userDto.getPassword()),
                        userDto.getPhone(),
                        userDto.getName());
                savedEntity = schoolRepository.save((School) user);
                break;
            case student:
                if (userDto.getFirstName() == null || userDto.getLastName() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                long schoolId;
                try {
                    schoolId = Long.parseLong(userDto.getSchoolId());
                } catch (NumberFormatException ignored) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                Long schoolYear = null;
                try {
                    schoolYear = Long.parseLong(userDto.getSchoolYear());
                } catch (NumberFormatException ignored) {
                }

//                create the Foreign key school
                Optional<School> school = Optional.of(new School());
                school = schoolRepository.findById(schoolId);
                if (school.isPresent()) {
                    School id = new School(school.get().getId(),
                            school.get().getEmail(),
                            null,
                            school.get().getPhone(),
                            school.get().getName());

                    user = new Student(null,
                            userDto.getEmail(),
                            passwordEncoder.encode(userDto.getPassword()),
                            userDto.getPhone(),
                            userDto.getFirstName(),
                            userDto.getLastName(),
                            userDto.getCity(),
                            userDto.getCountry(),
                            userDto.getAddress(),
                            false,
                            schoolYear, userDto.getMajor(), id);
                    savedEntity = studentRepository.save((Student) user);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(savedEntity);
    }
}
