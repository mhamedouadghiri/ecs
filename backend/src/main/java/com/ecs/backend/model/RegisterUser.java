package com.ecs.backend.model;

public class RegisterUser extends User{
    private String name;

    public RegisterUser(String email, String password, String phone, String name) {
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
