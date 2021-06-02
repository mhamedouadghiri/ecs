package com.ecs.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name = "company")
@Table(name = "company",
        uniqueConstraints = {
                @UniqueConstraint(name = "company_email_unique", columnNames = "email")
        })
public class Company extends  User{

    private String name;
    private String description;
    private String city;
    private String country;
    private String address;

    public Company() {
    }

    public Company(Long id, String email, String password, String phone, String name, String description, String city, String country, String address) {
        super(id, email, password, phone);
        this.name = name;
        this.description = description;
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
