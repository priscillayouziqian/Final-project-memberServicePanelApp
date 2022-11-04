package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.AppointmentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    //adding an appt
    @Transactional
    List<String> addAppt(AppointmentDto appointmentDto, Long memberId);

    //delete an appt
    @Transactional
    void deleteApptById(Long appointmentId);

    //update an appt
    @Transactional
    void updateApptById(AppointmentDto appointmentDto);

    //get all appt
    List<AppointmentDto> getAllApptById(Long memberId);

    //get a single appt by appt id
    Optional<AppointmentDto> getApptById(Long appointmentId);
}
