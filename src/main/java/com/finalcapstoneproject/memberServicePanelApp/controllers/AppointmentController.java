package com.finalcapstoneproject.memberServicePanelApp.controllers;

import com.finalcapstoneproject.memberServicePanelApp.dtos.AppointmentDto;
import com.finalcapstoneproject.memberServicePanelApp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    //Get all appointments by member id
    @GetMapping("/member/{memberId}")
    public List<AppointmentDto> getAppointmentByMember(@PathVariable Long memberId){
        return appointmentService.getAllApptById(memberId);
    }
    //add a new appointment
    @PostMapping("/member/{memberId}")
    public List<String> addAppointment(@RequestBody AppointmentDto appointmentDto,@PathVariable Long memberId){
        return appointmentService.addAppt(appointmentDto, memberId);
    }
    //delete an appointment
    @DeleteMapping("/{appointmentId}")
    public void deleteAppointmentById(@PathVariable Long appointmentId){
        appointmentService.deleteApptById(appointmentId);
    }
    //update an existing appointment
    @PutMapping
    public void updateAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.updateApptById(appointmentDto);
    }
    //get an appointment by the appointment id
    @GetMapping("/{appointmentId}")
    public Optional<AppointmentDto> getAppointmentById(@PathVariable Long appointmentId){
        return appointmentService.getApptById(appointmentId);
    }
}
