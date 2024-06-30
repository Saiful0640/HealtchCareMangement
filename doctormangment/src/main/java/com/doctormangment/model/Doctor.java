package com.doctormangment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "mobilenumber")
    private String mobileNumber;
    @Column(name = "degree")
    private String degree;
    @Column(name = "speciality")
    private String speciality;
}
