package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "internshipOffer")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class InternshipOffer {
    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    private Long id;

    private String title;
    private Integer duration;
    private Date startDate;
    private Date endDate;
    private String description;
    private Integer pay;
    private Boolean status;
    private String field;
    @ManyToOne
    private Company company;
}
