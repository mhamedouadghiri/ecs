package com.ecs.backend.util;

import com.ecs.backend.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class StudentUtils {

    public static ObjectNode studentInfoAsObjectNode(Student student,
                                                     List<Education> educations,
                                                     List<Experience> experiences,
                                                     List<Language> languages,
                                                     List<Skill> skills) {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.convertValue(student, JsonNode.class);

        ObjectNode studentObjectNode = jsonNode.deepCopy();

        ArrayNode educationsNode = mapper.createArrayNode();
        for (Education education : educations) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", education.getId());
            node.put("startDate", education.getStartDate() == null ? null : education.getStartDate().toString());
            node.put("endDate", education.getEndDate() == null ? null : education.getEndDate().toString());
            node.put("name", education.getName());
            node.put("level", education.getLevel());
            node.put("studentId", education.getStudent().getId());
            educationsNode.add(node);
        }
        studentObjectNode.set("educations", educationsNode);

        ArrayNode experiencesNode = mapper.createArrayNode();
        for (Experience experience : experiences) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", experience.getId());
            node.put("endDate", experience.getEndDate() == null ? null : experience.getEndDate().toString());
            node.put("startDate", experience.getStartDate() == null ? null : experience.getStartDate().toString());
            node.put("description", experience.getDescription());
            node.put("studentId", experience.getStudent().getId());
            experiencesNode.add(node);
        }
        studentObjectNode.set("experiences", experiencesNode);

        ArrayNode languagesNode = mapper.createArrayNode();
        for (Language language : languages) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", language.getId());
            node.put("name", language.getName());
            node.put("level", language.getLevel());
            node.put("studentId", language.getStudent().getId());
            languagesNode.add(node);
        }
        studentObjectNode.set("languages", languagesNode);

        ArrayNode skillsNode = mapper.createArrayNode();
        for (Skill skill : skills) {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", skill.getId());
            node.put("level", skill.getLevel());
            node.put("name", skill.getName());
            node.put("studentId", skill.getStudent().getId());
            skillsNode.add(node);
        }
        studentObjectNode.set("skills", skillsNode);

        return studentObjectNode;
    }
}
