package com.finalcapstoneproject.memberServicePanelApp.dtos;

import com.finalcapstoneproject.memberServicePanelApp.entities.Transportation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportationDto implements Serializable {
    private Long transportation_id;
    private String transportation_provider;
    private LocalTime pick_up_time;
    private LocalTime return_pick_up_time;
    private AppointmentDto appointmentDto;

    public TransportationDto(Transportation transportation){
        if(transportation.getTransportation_id() != null){
            this.transportation_id = transportation.getTransportation_id();
        }
        if(transportation.getTransportation_provider() != null){
            this.transportation_provider = transportation.getTransportation_provider();
        }
        if(transportation.getPick_up_time() != null){
            this.pick_up_time = transportation.getPick_up_time();
        }
        if(transportation.getReturn_pick_up_time() != null){
            this.return_pick_up_time = transportation.getReturn_pick_up_time();
        }
    }
}
