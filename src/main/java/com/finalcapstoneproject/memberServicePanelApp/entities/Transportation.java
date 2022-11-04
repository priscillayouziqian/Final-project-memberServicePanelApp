package com.finalcapstoneproject.memberServicePanelApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.finalcapstoneproject.memberServicePanelApp.dtos.TransportationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Transportation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String transportation_provider;

    @Column(columnDefinition = "text")
    @JsonFormat(pattern = "HH:mm:SSSZ", shape = JsonFormat.Shape.STRING)
    private LocalTime pick_up_time;

    @Column(columnDefinition = "text")
    @JsonFormat(pattern = "HH:mm:SSSZ", shape = JsonFormat.Shape.STRING)
    private LocalTime return_pick_up_time;

    @OneToOne
    @JsonBackReference
    private Appointment appointment;



    public Transportation(TransportationDto transportationDto){
        if(transportationDto.getTransportation_provider() != null){
            this.transportation_provider = transportationDto.getTransportation_provider();
        }
        if(transportationDto.getPick_up_time() != null){
            this.pick_up_time = transportationDto.getPick_up_time();
        }
        if(transportationDto.getReturn_pick_up_time() != null){
            this.return_pick_up_time = transportationDto.getReturn_pick_up_time();
        }
    }
}
