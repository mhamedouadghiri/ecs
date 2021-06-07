package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "internship")
@Table
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InternshipIdUsingIdclass.class)

public class Internship {
    @Id
    private Long internshipOfferId;
    @Id
    private Long studentId;
    private Integer grade;
}
