package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.TransportationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransportationService {
    //adding a transportation appt
    @Transactional
    void addTransportation(TransportationDto transportationDto, Long transportationId);

    //delete a transportation appt
    @Transactional
    void deleteTransportationById(Long transportationId);

    //get all transportation by appt id
    List<TransportationDto> getAllTransportationById(Long appointmentId);

    //get a single transportation appt by the transportation id
    Optional<TransportationDto> getTransportationById(Long appointmentId);
}
