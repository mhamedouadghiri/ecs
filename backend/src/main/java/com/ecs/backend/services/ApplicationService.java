package com.ecs.backend.services;

import com.ecs.backend.model.*;
import com.ecs.backend.repositories.*;
import com.ecs.backend.util.StudentUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final LanguageRepository languageRepository;
    private final SkillRepository skillRepository;

    public ApplicationService(ApplicationRepository applicationRepository, EducationRepository educationRepository, ExperienceRepository experienceRepository, LanguageRepository languageRepository, SkillRepository skillRepository) {
        this.applicationRepository = applicationRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.languageRepository = languageRepository;
        this.skillRepository = skillRepository;
    }

    public ResponseEntity<?> getApplications(Long offerId) {
        List<Application> applications = applicationRepository.findAllByInternshipOfferId(offerId);

        if (applications == null || applications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode allApplications = mapper.createArrayNode();
        for (Application application : applications) {
            try {
                JsonNode applicationNode = mapper.convertValue(application, JsonNode.class);
                ObjectNode jsonNode = applicationNode.deepCopy();

                Long studentId = application.getStudent().getId();

                ObjectNode studentInfo = StudentUtils.studentInfoAsObjectNode(application.getStudent(),
                        getEducations(studentId),
                        getExperiences(studentId),
                        getLanguages(studentId),
                        getSkills(studentId));
                jsonNode.set("studentInfo", studentInfo);

                allApplications.add(jsonNode);
            } catch (Exception ignored) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        return ResponseEntity.ok(allApplications.toString());
    }

    public ResponseEntity<?> getApplicationAndStudentInfoRegardingOffer(Long offerId, Long studentId) {
        Application application = applicationRepository.findByInternshipOfferIdAndStudentId(offerId, studentId);

        if (application != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();

                JsonNode applicationNode = mapper.convertValue(application, JsonNode.class);
                ObjectNode jsonNode = applicationNode.deepCopy();

                ObjectNode studentInfo = StudentUtils.studentInfoAsObjectNode(application.getStudent(),
                        getEducations(studentId),
                        getExperiences(studentId),
                        getLanguages(studentId),
                        getSkills(studentId));
                jsonNode.set("studentInfo", studentInfo);

                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

                return ResponseEntity.ok(json);
            } catch (Exception ignored) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private List<Education> getEducations(Long studentId) {
        return educationRepository.findAllByStudentId(studentId);
    }

    private List<Experience> getExperiences(Long studentId) {
        return experienceRepository.findAllByStudentId(studentId);
    }

    private List<Language> getLanguages(Long studentId) {
        return languageRepository.findAllByStudentId(studentId);
    }

    private List<Skill> getSkills(Long studentId) {
        return skillRepository.findAllByStudentId(studentId);
    }
}
