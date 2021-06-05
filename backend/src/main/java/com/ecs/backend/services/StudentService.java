package com.ecs.backend.services;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.model.Education;
import com.ecs.backend.model.Experience;
import com.ecs.backend.model.Student;
import com.ecs.backend.repositories.EducationRepository;
import com.ecs.backend.repositories.ExperienceRepository;
import com.ecs.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    final private EducationRepository educationRepository;
    final private StudentRepository studentRepository;
    final private ExperienceRepository experienceRepository;


    @Autowired
    public StudentService(EducationRepository educationRepository, StudentRepository studentRepository, ExperienceRepository experienceRepository) {
        this.educationRepository = educationRepository;

        this.studentRepository = studentRepository;
        this.experienceRepository = experienceRepository;
    }


    public ResponseEntity<?> getEducations(Long studentId) {
        List<Education> educations = educationRepository.findAllByStudentId(studentId);
        if (educations == null || educations.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(educations);
    }

    public ResponseEntity<?> saveEducation(UserDto user) {
        if (user.getName() == null || user.getLevel() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        long studentId;
        try {
            studentId = Long.parseLong(user.getStudentId());
        } catch (NumberFormatException ignored) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyy-MM-dd").parse(user.getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = new SimpleDateFormat("yyy-MM-dd").parse(user.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Optional<Student> student = Optional.of(new Student());
        student = studentRepository.findById(studentId);
        Education education = new Education(null,startDate,endDate,
                user.getName(), user.getLevel(),student.get());
        education = educationRepository.save(education);
        return ResponseEntity.ok(education);
    }

    public ResponseEntity<?> getExperiences(Long studentId) {
        List<Experience> experiences = experienceRepository.findAllByStudentId(studentId);
        if (experiences == null || experiences.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(experiences);
    }

    public ResponseEntity<?> saveExperience(UserDto user) {
        if (user.getDescription() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        long studentId;
        try {
            studentId = Long.parseLong(user.getStudentId());
        } catch (NumberFormatException ignored) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyy-MM-dd").parse(user.getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = new SimpleDateFormat("yyy-MM-dd").parse(user.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Optional<Student> student = Optional.of(new Student());
        student = studentRepository.findById(studentId);
        Experience experience = new Experience(null,startDate,endDate, user.getDescription(), student.get());
        experience = experienceRepository.save(experience);
        return ResponseEntity.ok(experience);
    }
}
