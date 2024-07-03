package com.doctormangment.IService;

import com.doctormangment.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatient {
    public boolean savePatient(Patient patient);
    public boolean updatePatient(Long id, Patient PatientDetails);
    public boolean deletePatient(Long id);
    public Optional<Patient> getPatientById(Long id);
    public List<Patient> getAllPatient();


}
