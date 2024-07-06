package com.doctormangment.ServiceImplement;


import com.doctormangment.IService.IAppointment;
import com.doctormangment.model.Appointment;
import com.doctormangment.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppoinmentService implements IAppointment {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Override
    public boolean saveAppointment(Appointment appointment) {

        Appointment appointment1 = appointmentRepo.save(appointment);

        if (appointment1.getId()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateAppointment(Long id, Appointment appointmentDetails) {

        Appointment appointment = appointmentRepo.findById(id).orElseThrow(()->new RuntimeException("Appointment not Found by this ID"));

        appointment.setAppointmentNumber(appointmentDetails.getAppointmentNumber());
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setDoctorId(appointmentDetails.getDoctorId());
        appointment.setPatientId(appointmentDetails.getPatientId());
        appointment.setStatus(appointmentDetails.getStatus());

        Appointment appointment1 = appointmentRepo.save(appointment);

        if (appointment1.getId()>0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean deleteAppointment(Long id) {
       Optional<Appointment> appointment = appointmentRepo.findById(id);

        if (appointment.isPresent()){
            appointmentRepo.deleteById(id);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepo.findById(id);
        if (appointment.isPresent()){
            return appointment;
        }else {
            return Optional.empty();
        }

    }

    @Override
    public List<Appointment> getAllAppointments() {
        List<Appointment>appointmentList = appointmentRepo.findAll();

        if (appointmentList.isEmpty()){
            return null;
        }else {
            return appointmentList;
        }

    }
}
