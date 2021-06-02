package com.ecs.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class ApplicationIdUsingIdclass implements Serializable {
    private Long internshipOfferId;
    private Long studentId;
}
