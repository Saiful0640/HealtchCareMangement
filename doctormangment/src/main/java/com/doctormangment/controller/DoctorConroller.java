package com.doctormangment.controller;


import com.doctormangment.IService.IDoctor;
import com.doctormangment.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class DoctorConroller {
    @Autowired
    IDoctor iDoctor;

    @PostMapping("/doctorSave")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor saveDoctor = iDoctor.saveDoctor(doctor);
        return new ResponseEntity<>(saveDoctor, HttpStatus.CREATED);
    }
}
