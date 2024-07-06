package com.doctormangment.ServiceImplement;

import com.doctormangment.IService.IPatient;
import com.doctormangment.model.Patient;
import com.doctormangment.model.Role;
import com.doctormangment.repository.PatientRepo;
import com.doctormangment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService implements IPatient {

    @Autowired
    PatientRepo patientRepo;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Patient  savePatient(Patient patient) {
        Set<Role> roles = patient.getRoles();
        Set<Role> persistedRoles = new HashSet<>();
        for (Role role : roles) {
            Role existingRole = roleRepository.findByName(role.getName());
            if (existingRole != null) {
                persistedRoles.add(existingRole);
            } else {
                // Optionally save the new role if it doesn't exist
                existingRole = roleRepository.save(role);
                persistedRoles.add(existingRole);
            }
        }
        patient.setRoles(persistedRoles);
        return patientRepo.save(patient);
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
