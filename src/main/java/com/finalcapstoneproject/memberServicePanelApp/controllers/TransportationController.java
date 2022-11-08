package com.finalcapstoneproject.memberServicePanelApp.controllers;

import com.finalcapstoneproject.memberServicePanelApp.dtos.TransportationDto;
import com.finalcapstoneproject.memberServicePanelApp.services.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/transportation")
public class TransportationController {
    @Autowired
    private TransportationService transportationService;

    //get all transportation info by appointment id
    @GetMapping("/appointment/{appointmentId}")
    public List<TransportationDto> getTransportationByAppt(@PathVariable Long appointmentId){
        return transportationService.getAllTransportationById(appointmentId);
    }
    //add a new transportation
    @PostMapping("/appointment/{appointmentId}")
    public void addTransportation(@RequestBody TransportationDto transportationDto,@PathVariable Long appointmentId){
        transportationService.addTransportation(transportationDto, appointmentId);
    }
    //delete a transportation
    @DeleteMapping("/{transportationId}")
    public void deleteTransportation(@PathVariable Long transportationId){
        transportationService.deleteTransportationById(transportationId);
    }
    //get a transportation by transportation id
//    @GetMapping("/{transportationId}")
//    public Optional<TransportationDto> getTransportationById(@PathVariable Long transportationId){
//        return transportationService.getTransportationById(transportationId);
//    }
//
//    //get a transportation by appointment id
//    @GetMapping("/{appointmentId}")
//    public Optional<TransportationDto> getTransportationById(@PathVariable Long appointmentId){
//        return transportationService.getTransportationById(appointmentId);
//    }

}
