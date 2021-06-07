package com.ecs.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ApplicationPK implements Serializable {

    private Long studentId;
    private Long internshipOfferId;
}
