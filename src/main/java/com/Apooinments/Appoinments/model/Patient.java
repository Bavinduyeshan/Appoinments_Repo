package com.Apooinments.Appoinments.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Patient {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false) // Foreign key to the users table
//    private Integer user;

    //    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false) // Foreign key to the users table
//    private User user;

    private String firstname;
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private LocalDate dateOfBirth;

    @Lob
    private String address;

    private String gender;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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
