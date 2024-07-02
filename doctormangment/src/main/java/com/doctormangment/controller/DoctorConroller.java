package com.doctormangment.controller;


import com.doctormangment.IService.IDoctor;
import com.doctormangment.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @PutMapping("/doctorUpdate/{id}")
    public ResponseEntity <?> doctorUpdate(@PathVariable Long id, @RequestBody Doctor doctorDetails ){

        try{
            Doctor doctorOne = iDoctor.updateDoctor(id, doctorDetails);
            if(doctorOne != null){

                return new ResponseEntity<>("Doctor Updated Successfully", HttpStatus.OK);
            }else {
                return  new ResponseEntity<>("Doctor info Updated Faild", HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            HashMap<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
                return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
