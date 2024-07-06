package com.doctormangment.ServiceImplement;

import com.doctormangment.IService.IDoctor;
import com.doctormangment.exception.DoctorNotFoundException;
import com.doctormangment.model.Doctor;
import com.doctormangment.model.Role;
import com.doctormangment.repository.DoctorRepo;
import com.doctormangment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorService implements IDoctor {

    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {

        /*if(doctor==null){
            throw new IllegalArgumentException("data can't be null");
        }

        try{
            Doctor doctor1 = doctorRepo.save(doctor);

            return doctor1;
        }catch (Exception e){
             throw new RuntimeException("faild to save Doctor", e);
        }*/

        Set<Role> roles = doctor.getRoles();
        Set<Role> persistedRoles = new HashSet<>();
        for (Role role : roles) {
            Role existingRole = roleRepository.findByName(role.getName());
            if (existingRole != null) {
                persistedRoles.add(existingRole);
            } else {
                existingRole = roleRepository.save(role);
                persistedRoles.add(existingRole);
            }
        }

        // Set the persisted roles back to the doctor
        doctor.setRoles(persistedRoles);

        // Save the doctor
        return doctorRepo.save(doctor);

    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = doctorRepo.findById(id).orElseThrow(()->new RuntimeException("no Doctor found"));

        doctor.setDegree(doctorDetails.getDegree());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setMobileNumber(doctorDetails.getMobileNumber());
        doctor.setName(doctorDetails.getName());
        doctor.setPassword(doctorDetails.getPassword());
        doctor.setSpeciality(doctorDetails.getSpeciality());
        doctor.setUsername(doctorDetails.getUsername());

        Doctor doctor1 = doctorRepo.save(doctor);
        return doctor1;
    }

    @Override
    public String deleteDoctor(Long id) {

        try {
            Optional<Doctor> doctor = doctorRepo.findById(id);

            if (doctor.isPresent()){
                doctorRepo.deleteById(id);


                return "Doctor Delete Successfully";
            }else {

                return "Doctor delete failed";
            }
        }catch (Exception e){
                throw   new DoctorNotFoundException("Error Occuer while deleting Doctor", e);
        }

    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        try {
            Optional<Doctor> doctor = doctorRepo.findById(id);
            if (doctor != null && doctor.isPresent()){
                return doctor;
            }else {
                return null;
            }
        }catch (Exception e){
        throw new DoctorNotFoundException("Error occure while finding the doctor", e);
        }


    }

    @Override
    public List<Doctor> getAllDoctors() {

        try {
            List<Doctor> doctorList = doctorRepo.findAll();
            if (doctorList.isEmpty()){
                throw new DoctorNotFoundException("Doctor not Available ");
            }else {
                return doctorList;
            }

        }catch (Exception e){
            throw new DoctorNotFoundException("Erro Occuer While Finding Doctor", e);
        }
    }
}
