package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "education")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String level;
    @ManyToOne
    private Student student;
}
