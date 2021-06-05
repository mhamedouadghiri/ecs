package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private Date startDate;
    private Date endDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String level;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "student_id",updatable = false, nullable = false)
    private Student student;
}
