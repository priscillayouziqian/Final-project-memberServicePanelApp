package com.finalcapstoneproject.memberServicePanelApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.finalcapstoneproject.memberServicePanelApp.dtos.AppointmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appt_id;

    @Column(columnDefinition = "text")
    private String provider_name;

    @Column(columnDefinition = "text")
    private String provider_address;

    @Column(columnDefinition = "text")
    private LocalDateTime appt_date_time;

    @ManyToOne
    @JsonBackReference
    private Member member;

    @OneToOne(mappedBy = "appointment")
    @JsonBackReference
    private Transportation transportation;

    public Appointment(AppointmentDto appointmentDto){
        if(appointmentDto.getProvider_name() != null){
            this.provider_name = appointmentDto.getProvider_name();
        }
        if(appointmentDto.getProvider_address() != null){
            this.provider_address = appointmentDto.getProvider_address();
        }
        if(appointmentDto.getAppt_date_time() != null){
            this.appt_date_time = appointmentDto.getAppt_date_time();
        }
    }
}
