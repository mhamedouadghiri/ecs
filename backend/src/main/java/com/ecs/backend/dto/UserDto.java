package com.ecs.backend.dto;

import com.ecs.backend.model.School;
import com.ecs.backend.model.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private UserType userType;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String description;
    private String city;
    private String country;
    private String address;
    private String firstName;
    private String lastName;
    private String major;
    private String schoolYear;
    private String schoolId;
}
