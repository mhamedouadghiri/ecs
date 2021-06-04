package com.ecs.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Transient
    private UserType userType;

    public User(Long id, String email, String password, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
