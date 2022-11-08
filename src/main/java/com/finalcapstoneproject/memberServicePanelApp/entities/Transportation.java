package com.finalcapstoneproject.memberServicePanelApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finalcapstoneproject.memberServicePanelApp.dtos.TransportationDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Transportation")
//@Data
@Setter
@Getter
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

//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "appointment_id", referencedColumnName = "appt_id")
//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "appt_id")
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

    @Override
    public String toString() {
        return "Transportation{" +
                "id=" + id +
                ", transportation_provider='" + transportation_provider + '\'' +
                ", pick_up_time=" + pick_up_time +
                ", return_pick_up_time=" + return_pick_up_time +
                '}';
    }
}
