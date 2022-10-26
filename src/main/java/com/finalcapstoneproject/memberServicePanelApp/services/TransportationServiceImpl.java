package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.TransportationDto;
import com.finalcapstoneproject.memberServicePanelApp.entities.Appointment;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import com.finalcapstoneproject.memberServicePanelApp.entities.Transportation;
import com.finalcapstoneproject.memberServicePanelApp.repositories.AppointmentRepository;
import com.finalcapstoneproject.memberServicePanelApp.repositories.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportationServiceImpl implements TransportationService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TransportationRepository transportationRepository;

    //adding a transportation appt
    @Override
    @Transactional
    public void addTransportation(TransportationDto transportationDto, Long transportationId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(transportationId);
        Transportation transportation = new Transportation(transportationDto);
        if(appointmentOptional.isPresent()){
            transportation.setAppointment(appointmentOptional.get());
        }
//        appointmentOptional.ifPresent(transportation::setAppointment);
        transportationRepository.saveAndFlush(transportation);
    }
    //delete a transportation appt
    @Override
    @Transactional
    public void deleteTransportationById(Long transportationId){
        Optional<Transportation> transportationOptional = transportationRepository.findById(transportationId);
        transportationOptional.ifPresent(transportation -> transportationRepository.delete(transportation));
    }
    //get all transportation by appt id
    @Override
    public List<TransportationDto> getAllTransportationById(Long appointmentId){
        Optional<Appointment> transportationOptional = appointmentRepository.findById(appointmentId);
        if(transportationOptional.isPresent()){
            List<Transportation> transportationList = transportationRepository.findAllByAppointmentEquals(transportationOptional.get());
            return transportationList.stream().map(transportation -> new TransportationDto(transportation)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    //get a single transportation appt by the transportation id
    @Override
    public Optional<TransportationDto> getTransportationById(Long appointmentId){
        Optional<Transportation> transportationOptional = transportationRepository.findById(appointmentId);
        if(transportationOptional.isPresent()){
            return Optional.of(new TransportationDto(transportationOptional.get()));
        }
        return Optional.empty();
    }
}
