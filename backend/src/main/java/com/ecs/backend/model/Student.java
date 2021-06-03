package com.ecs.backend.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name="student")
@Table(name ="student",
    uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique", columnNames = "email")
    })

public class Student extends  User{
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String address;
    private Boolean status;
    private Long schoolYear;
    private String major;
    //@ManyToOne
    //private School school;

    public Student() {
    }

    public Student(Long id, String email, String password, String phone,String firstName, String lastName, String city, String country, String address, Boolean status, Long schoolYear, String major) {
        super(email, password, phone);
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.address = address;
        this.status = status;
        this.schoolYear = schoolYear;
        this.major = major;
        //this.school = schoolId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Long schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    //public School getSchool() {
        //return school;
    //}

    //public void setSchool(School school) {
       // this.school = school;
    //}
}
