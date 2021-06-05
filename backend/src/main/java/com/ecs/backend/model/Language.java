package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "language")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    private Long id;
    private String name;
    private String level;
    @ManyToOne
    private Student student;

}
