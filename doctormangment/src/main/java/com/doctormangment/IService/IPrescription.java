package com.doctormangment.IService;

import com.doctormangment.model.Prescription;

import java.util.List;
import java.util.Optional;

public interface IPrescription {
    public boolean savePrescription(Prescription prescription);
    public  boolean updatePrescription(Long id,Prescription prescriptionDetails);
    public boolean deletePrescription(Long id);
    public Optional<Prescription> getGetAllPrescriptionById(Long id);
    public List<Prescription> getAllPrescription();
}
