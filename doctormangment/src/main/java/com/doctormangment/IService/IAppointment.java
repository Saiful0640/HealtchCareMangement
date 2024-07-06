package com.doctormangment.IService;

import com.doctormangment.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointment {

    public boolean saveAppointment(Appointment appointment);
    public  boolean updateAppointment(Long id,Appointment appointmentDetails);
    public boolean deleteAppointment(Long id);
    public Optional<Appointment> getAppointmentById(Long id);
    public List<Appointment> getAllAppointments();
}
