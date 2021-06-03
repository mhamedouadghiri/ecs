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
    @GeneratedValue(
            strategy = IDENTITY
    )
    private Long id;

    public User(String email, String password, String phone,UserType userType) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userType = userType;
    }

    private String email;
    private String password;
    private String phone;
    private UserType userType;

}
