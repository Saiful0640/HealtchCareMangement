package com.doctormangment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Doctor extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "mobilenumber")
    private String mobileNumber;
    @Column(name = "degree")
    private String degree;
    @Column(name = "speciality")
    private String speciality;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctor_role",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
