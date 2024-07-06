package com.doctormangment.controller;

import com.doctormangment.IService.IAppointment;
import com.doctormangment.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class AppoinmentController {

    @Autowired
    IAppointment iAppointment;

    @PostMapping("/SaveAppointment")
    public ResponseEntity<String> appointementSave(@RequestBody Appointment appointment){

        try {
            boolean appoinemnt1 = iAppointment.saveAppointment(appointment);
            if (appoinemnt1){
                return new ResponseEntity<>("Appoinemtn Save Successfully", HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("Appoinemtn saving faild", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            throw new RuntimeException("Error occuer while Saving Appoinemtn :" ,e);
        }
    }

    @PutMapping("/updateAppointment/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {

        try {
            boolean isAppoinemtUpdate = iAppointment.updateAppointment(id, appointmentDetails);
            if (isAppoinemtUpdate) {
                return new ResponseEntity<>("Appointment Update Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Appointment not Update", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @GetMapping("/getAppointment/{id}")
    public ResponseEntity<Appointment> getAppointmentById (@PathVariable Long id){

        try {
            Optional<Appointment> appointment = iAppointment.getAppointmentById(id);
            if (appointment.isPresent()){
                return new ResponseEntity<>(appointment.get(),HttpStatus.OK );
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @GetMapping("/getAllAppointment")
    public ResponseEntity<List<Appointment>> allPatient(){

        try {
            List<Appointment> appointmentList= iAppointment.getAllAppointments();
            if (appointmentList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(appointmentList, HttpStatus.OK);
            }
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage(), e);
        }
    }

}
