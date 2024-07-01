package com.doctormangment.IService;

import com.doctormangment.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatient {
    public Patient savePatient(Patient patient);
    public Patient updatePatient(Long id, Patient PatientDetails);
    public String deletePatient(Long id);
    public Optional<Patient> getPatientById(Long id);
    public List<Patient> getAllPatient();


}
