package com.ecs.backend.services;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.model.Company;
import com.ecs.backend.model.School;
import com.ecs.backend.model.Student;
import com.ecs.backend.model.User;
import com.ecs.backend.repositories.CompanyRepository;
import com.ecs.backend.repositories.SchoolRepository;
import com.ecs.backend.repositories.StudentRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final SchoolRepository schoolRepository;
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;

//    @Autowired
//    private  final SecurityConfig securityConfig;




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
        if (persistedUser != null && BCrypt.checkpw(user.getPassword(),persistedUser.getPassword())) {
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
        User savedEntity = null;
        switch (userDto.getUserType()) {

            case company:
                if (userDto.getName() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                String passwordCompany = BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt());
                user = new Company(null,
                        userDto.getEmail(),
                        passwordCompany,  // TODO: BCrypt is Done
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
                //i wanna check here if the this school exist then i do save because without this if when the user already exist
                // then the id increment even we didn't insert any school
                String passwordScool = BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt());
                user = new School(null,
                        userDto.getEmail(),
                        passwordScool,  // TODO: BCrypt is Done
                        userDto.getPhone(),
                        userDto.getName());
                savedEntity = schoolRepository.save((School) user);
                break;
            case student:
                // do checks
                if (userDto.getFirstName() == null || userDto.getLastName() ==null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                // new Student
//                1ere méthode j'envoie un objet school qui contient just l'id du school
                Long schoolId = null;
                try {
                    schoolId = Long.parseLong(userDto.getSchoolId());
                } catch (NumberFormatException ignored) {
                }

                Long schoolYear = null;
                try {
                    schoolYear = Long.parseLong(userDto.getSchoolYear());
                } catch (NumberFormatException ignored) {
                }
//                user = new Student(null,
//                            userDto.getEmail(),
//                            userDto.getPassword(),
//                            userDto.getPhone(),
//                            userDto.getFirstName(),
//                            userDto.getLastName(),
//                            userDto.getCity(),
//                            userDto.getCountry(),
//                            userDto.getAddress(),
//                            false,
//                            schoolYear, userDto.getMajor(), userDto.getSchoolId());
//                    savedEntity = studentRepository.save((Student) user);

                // 2eme méthode j'envoie un objet student qui contient l'id school et dans le backend où je traite l'existance de cette école


//                create the Foreign key school
                Optional<School> school = Optional.of(new School());
                school = schoolRepository.findById(schoolId);
                if (school.isPresent()){

                    School id = new School(school.get().getId(),
                            school.get().getEmail(),
                            null,
                            school.get().getPhone(),
                            school.get().getName()) ;

                    String passwordStudent = BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt());
                    user = new Student(null,
                            userDto.getEmail(),
                            passwordStudent,
                            userDto.getPhone(),
                            userDto.getFirstName(),
                            userDto.getLastName(),
                            userDto.getCity(),
                            userDto.getCountry(),
                            userDto.getAddress(),
                            false,
                            schoolYear, userDto.getMajor(), id);
                    savedEntity = studentRepository.save((Student) user);
                }else {
                    throw new IllegalStateException("Cette School n'éxiste pas dans la base de donné");
                }

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
