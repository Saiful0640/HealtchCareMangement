package com.doctormangment.ServiceImplement;

import com.doctormangment.IService.IPatient;
import com.doctormangment.model.Patient;
import com.doctormangment.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatient {

    @Autowired
    PatientRepo patientRepo;

    @Override
    public boolean savePatient(Patient patient) {
        try {
            Patient patient1 = patientRepo.save(patient);
            if (patient1 !=null){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException("Error occuer While Saving Patient", e);

        }
    }

    @Override
    public boolean updatePatient(Long id, Patient patientDetails) {

        Patient patient1 = patientRepo.findById(id).orElseThrow(()->new RuntimeException("Patient Not Found BY this ID"));

        patient1.setEmail(patientDetails.getEmail());
        patient1.setMobileNumber(patientDetails.getMobileNumber());
        patient1.setName(patientDetails.getName());
        patient1.setUsername(patientDetails.getUsername());
        patient1.setPassword(patientDetails.getPassword());


       return patientRepo.save(patient1) != null;


    }

    @Override
    public boolean deletePatient(Long id) {
        try {
            Optional<Patient> optionalPatient = patientRepo.findById(id);
            if (optionalPatient.isPresent()){
                patientRepo.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public Optional<Patient> getPatientById(Long id) {

        try {
            Optional<Patient> optionalPatient = patientRepo.findById(id);
            if (optionalPatient.isPresent()){
                return optionalPatient;
            }else {
                return Optional.empty();
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public List<Patient> getAllPatient() {

        try {
            List<Patient> patientList = patientRepo.findAll();
            if (patientList.isEmpty()){
                return Collections.emptyList();
            }else {
                return patientList;
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}
