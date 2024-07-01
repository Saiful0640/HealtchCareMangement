package com.doctormangment.ServiceImplement;

import com.doctormangment.IService.IDoctor;
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
        return null;
    }

    @Override
    public void deleteDoctor(Long id) {

    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return null;
    }
}
