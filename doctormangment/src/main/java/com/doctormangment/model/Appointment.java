package com.doctormangment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="appointmentnumber",unique = true)
    private Long appointmentNumber;
    @Column(name="Patientid")
    private Long patientId;
    @Column(name="doctorid")
    private Long doctorId;
    @Column(name="appointmentdate")
    private LocalDateTime appointmentDate;
    @Column(name="status")
    private String status;
}
