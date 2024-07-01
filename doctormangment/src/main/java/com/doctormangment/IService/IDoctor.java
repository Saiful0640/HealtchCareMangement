package com.doctormangment.IService;

import com.doctormangment.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface IDoctor {
    public Doctor saveDoctor(Doctor doctor);
    public  Doctor updateDoctor(Long id,Doctor doctorDetails);
    public void deleteDoctor(Long id);
    public Optional<Doctor> getDoctorById(Long id);
    public List<Doctor> getAllDoctors();
}
