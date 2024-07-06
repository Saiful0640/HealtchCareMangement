package com.doctormangment.controller;

import com.doctormangment.IService.IPatient;
import com.doctormangment.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class PatientController {

    @Autowired
    IPatient iPatient;

    @PostMapping("/SavePatient")
    public ResponseEntity<String>patientSave(@RequestBody Patient patient){

        try {
            Patient patient1 = iPatient.savePatient(patient);
            if (patient1 != null ){
                return new ResponseEntity<>("Patient Save Successfully", HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("Patient saving faild", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            throw new RuntimeException("Error occuer while Saving :" ,e);
        }
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<String> updatePatient( @PathVariable Long id,@RequestBody Patient patientdetails) {

        try {
            boolean isPatientUpdate = iPatient.updatePatient(id, patientdetails);
            if (isPatientUpdate) {
                return new ResponseEntity<>("Patient Update Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Patient not Update", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @GetMapping("/getPatient/{id}")
    public ResponseEntity<Patient> getPatientById (@PathVariable Long id){

        try {
            Optional<Patient> patient = iPatient.getPatientById(id);
            if (patient.isPresent()){
                return new ResponseEntity<>(patient.get(),HttpStatus.OK );
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @GetMapping("/getAllPatient")
    public ResponseEntity<List<Patient>> allPatient(){

        try {
            List<Patient> patientList= iPatient.getAllPatient();
            if (patientList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(patientList, HttpStatus.OK);
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(), e);
        }
    }

}
