package com.doctormangment.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Embeddable
@Data
public class PrescriptionDetails {

    @ElementCollection
    private List<String> advise;

    @ElementCollection
    private List<String> medicine;

    @ElementCollection
    private List<String> testName;
}
