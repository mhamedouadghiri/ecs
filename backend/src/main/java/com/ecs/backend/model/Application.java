package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "application")
@Table
@IdClass(ApplicationIdUsingIdclass.class)
public class Application {
    @Id
    private Long internshipOfferId;
    @Id
    private Long studentId;
    @Column(columnDefinition = "TEXT")
    private String coverLetter;
    private Boolean request;  // company's call for interview
    private Boolean answer;  // student's answer
    private Boolean result;
}
