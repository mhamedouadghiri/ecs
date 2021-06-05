package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "experience")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    private Long id;
    private Date startDate;
    private Date endDate;
    private String description;
    @ManyToOne
    private Student student;
}
