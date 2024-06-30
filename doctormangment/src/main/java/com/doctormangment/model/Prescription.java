package com.doctormangment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long appointmentId;

    @Embedded
    private PrescriptionDetails prescriptionDetails;


}
