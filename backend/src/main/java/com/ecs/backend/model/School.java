package com.ecs.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name = "school")
@Table(name = "school",
        uniqueConstraints = {
                @UniqueConstraint(name = "school_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "school_name_unique", columnNames = "name")
        }
)
public class School extends User {

    @Column(nullable = false)
    private String name;

    public School() {
    }

    public School(Long id, String email, String password, String phone, String name) {
        super(id, email, password, phone);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
