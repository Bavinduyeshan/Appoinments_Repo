package com.Apooinments.Appoinments.model;

import java.time.LocalDate;
import java.util.List;

public class PatientResponse {


    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false) // Foreign key to the users table
//    private Integer user;

    //    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false) // Foreign key to the users table
//    private User user;

    private String firstname;
    private String lastname;


    private String email;

    private String phone;
    private LocalDate dateOfBirth;


    private String address;

    private String gender;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
