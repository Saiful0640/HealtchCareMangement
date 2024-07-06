package com.doctormangment.controller;


import com.doctormangment.IService.IPrescription;
import com.doctormangment.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class PrescriptionController {

    @Autowired
    IPrescription iPrescription;

    @PostMapping("/SavePrescription")
    public ResponseEntity<String> prescriptionSave(@RequestBody Prescription prescription){

        try {
            boolean prescription1 = iPrescription.savePrescription(prescription);
            if (prescription1){
                return new ResponseEntity<>("Prescription Save Successfully", HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("Prescription saving faild", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            throw new RuntimeException("Error occuer while saving  Prescription :" ,e);
        }
    }

    @PutMapping("/updatePrescription/{id}")
    public ResponseEntity<String> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescriptionDetails) {

        try {
            boolean isPrescriptionUpdate = iPrescription.updatePrescription(id, prescriptionDetails);
            if (isPrescriptionUpdate) {
                return new ResponseEntity<>("Prescription Update Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Prescription not Update", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @GetMapping("/getPrescription/{id}")
    public ResponseEntity<Prescription> getPrescriptionById (@PathVariable Long id){

        try {
            Optional<Prescription> prescription = iPrescription.getGetAllPrescriptionById(id);
            if (prescription.isPresent()){
                return new ResponseEntity<>(prescription.get(),HttpStatus.OK );
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @GetMapping("/getAllprescription")
    public ResponseEntity<List<Prescription>> allprescription(){

        try {
            List<Prescription> prescriptionList= iPrescription.getAllPrescription();
            if (prescriptionList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(prescriptionList, HttpStatus.OK);
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(), e);
        }
    }

}
