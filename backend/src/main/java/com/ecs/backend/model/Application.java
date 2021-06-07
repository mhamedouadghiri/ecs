package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "application")
@IdClass(ApplicationPK.class)
public class Application {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Id
    @Column(name = "internship_offer_id")
    private Long internshipOfferId;

    @ManyToOne
    @JoinColumn(name = "internship_offer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private InternshipOffer internshipOffer;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @Column(columnDefinition = "TEXT")
    private String coverLetter;

    private Boolean request;  // company's call for interview

    private Boolean answer;  // student's answer

    private Boolean result;
}
