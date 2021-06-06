package com.ecs.backend.services;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.model.*;
import com.ecs.backend.repositories.*;
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

    private final EducationRepository educationRepository;
    private final StudentRepository studentRepository;
    private final ExperienceRepository experienceRepository;
    private final LanguageRepository languageRepository;
    private final SkillRepository skillRepository;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public StudentService(EducationRepository educationRepository, StudentRepository studentRepository, ExperienceRepository experienceRepository, LanguageRepository languageRepository, SkillRepository skillRepository, ApplicationRepository applicationRepository) {
        this.educationRepository = educationRepository;
        this.studentRepository = studentRepository;
        this.experienceRepository = experienceRepository;
        this.languageRepository = languageRepository;
        this.skillRepository = skillRepository;
        this.applicationRepository = applicationRepository;
    }

    // Educations endpoint
    public ResponseEntity<?> getEducations(Long studentId) {
        List<Education> educations = educationRepository.findAllByStudentId(studentId);
        if (educations == null || educations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(educations);
    }

    public ResponseEntity<?> saveEducation(UserDto user) {
        if (user.getName() == null || user.getLevel() == null) {
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
        Education education = new Education(null, startDate, endDate,
                user.getName(), user.getLevel(), student.get());
        education = educationRepository.save(education);
        return ResponseEntity.ok(education);
    }

    // Experiences endpoint
    public ResponseEntity<?> getExperiences(Long studentId) {
        List<Experience> experiences = experienceRepository.findAllByStudentId(studentId);
        if (experiences == null || experiences.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(experiences);
    }

    public ResponseEntity<?> saveExperience(UserDto user) {
        if (user.getDescription() == null) {
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
        Experience experience = new Experience(null, startDate, endDate, user.getDescription(), student.get());
        experience = experienceRepository.save(experience);
        return ResponseEntity.ok(experience);
    }

    //Languages Endpoint
    public ResponseEntity<?> getLanguages(Long studentId) {
        List<Language> languages = languageRepository.findAllByStudentId(studentId);
        if (languages == null || languages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(languages);
    }

    public ResponseEntity<?> saveLanguage(UserDto user) {
        if (user.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        long studentId;
        try {
            studentId = Long.parseLong(user.getStudentId());
        } catch (NumberFormatException ignored) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Student> student = Optional.of(new Student());
        student = studentRepository.findById(studentId);
        Language language = new Language(null, user.getName(), user.getLevel(), student.get());
        language = languageRepository.save(language);
        return ResponseEntity.ok(language);
    }


    //Skills Endpoint
    public ResponseEntity<?> getSkills(Long studentId) {
        List<Skill> skills = skillRepository.findAllByStudentId(studentId);
        if (skills == null || skills.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(skills);
    }

    public ResponseEntity<?> saveSkill(UserDto user) {
        if (user.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        long studentId;
        try {
            studentId = Long.parseLong(user.getStudentId());
        } catch (NumberFormatException ignored) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Optional<Student> student = Optional.of(new Student());
        student = studentRepository.findById(studentId);
        Skill skill = new Skill(null, user.getName(), user.getLevel(), student.get());
        skill = skillRepository.save(skill);
        return ResponseEntity.ok(skill);
    }

    public ResponseEntity<?> apply(Application application) {
        Application save = applicationRepository.save(application);
        return ResponseEntity.ok(save);
    }
}
