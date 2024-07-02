package com.doctormangment.ServiceImplement;

import com.doctormangment.IService.IDoctor;
import com.doctormangment.exception.DoctorNotFoundException;
import com.doctormangment.model.Doctor;
import com.doctormangment.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctor {

    @Autowired
    DoctorRepo doctorRepo;

    @Override
    public Doctor saveDoctor(Doctor doctor) {

        if(doctor==null){
            throw new IllegalArgumentException("data can't be null");
        }

        try{
            Doctor doctor1 = doctorRepo.save(doctor);

            return doctor1;
        }catch (Exception e){
             throw new RuntimeException("faild to save Doctor", e);
        }

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
    public void deleteDoctor(Long id) {

    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        try {
            Optional<Doctor> doctor = doctorRepo.findById(id);
            if (doctor.isPresent()){
                return doctor;
            }else {
                throw new DoctorNotFoundException("Doctor not found with this id");
            }
        }catch (Exception e){
        throw new DoctorNotFoundException("Error occure while finding the doctor", e);
        }


    }

    @Override
    public List<Doctor> getAllDoctors() {
        return null;
    }
}
