package com.ecs.backend.model;

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
public class School extends  User{

    private String name;
    public School() {
    }

    public School(Long id,
                  String email,
                  String password,
                  String phone,
                  UserType userType,
                  String name) {
        super(id, email, password, phone,userType);
        this.name = name;
    }

    public School(String email,
                  String password,
                  String phone,
                  UserType userType,
                  String name) {
        super(email, password, phone);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
