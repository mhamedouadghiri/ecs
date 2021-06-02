package com.ecs.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@Data
public class InternshipIdUsingIdclass implements Serializable {
    private Long internshipOfferId;
    private Long studentId;
}
