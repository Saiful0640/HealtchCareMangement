package com.doctormangment.ServiceImplement;

import com.doctormangment.IService.IPrescription;
import com.doctormangment.model.Prescription;
import com.doctormangment.repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService implements IPrescription {

    @Autowired
    PrescriptionRepo prescriptionRepo;


    @Override
    public boolean savePrescription(Prescription prescription) {

        Prescription prescription1 = prescriptionRepo.save(prescription);

        if (prescription1.getId()>0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean updatePrescription(Long id, Prescription prescriptionDetails) {

        Prescription prescription = prescriptionRepo.findById(id).orElseThrow(()-> new RuntimeException("Prescription Not found by this id"));

        prescription.setAppointmentId(prescriptionDetails.getAppointmentId());
        prescription.setPrescriptionDetails(prescriptionDetails.getPrescriptionDetails());

        Prescription prescription1 = prescriptionRepo.save(prescription);

        if (prescription1.getId()>0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean deletePrescription(Long id) {
        Optional<Prescription> prescription = prescriptionRepo.findById(id);

        if (prescription.isPresent()){
            prescriptionRepo.deleteById(id);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public Optional<Prescription> getGetAllPrescriptionById(Long id) {

        Optional<Prescription> prescription = prescriptionRepo.findById(id);

        if (prescription.isPresent()) {
            return prescription;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Prescription> getAllPrescription() {

        List<Prescription> prescriptionList = prescriptionRepo.findAll();
        if (prescriptionList.isEmpty()){
            return null;
        }else {
            return prescriptionList;
        }

    }
}
