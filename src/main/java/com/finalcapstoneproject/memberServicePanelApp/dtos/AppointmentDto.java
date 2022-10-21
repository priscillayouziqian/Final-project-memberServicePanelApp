package com.finalcapstoneproject.memberServicePanelApp.dtos;

import com.finalcapstoneproject.memberServicePanelApp.entities.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto implements Serializable {
    private Long  appt_id;
    private String provider_name;
    private String provider_address;
    private LocalDateTime appt_date_time;
    private MemberDto memberDto;

    public AppointmentDto(Appointment appointment){
        if(appointment.getAppt_id() != null){
            this.appt_id = appointment.getAppt_id();
        }
        if(appointment.getProvider_name() != null){
            this.provider_name = appointment.getProvider_name();
        }
        if(appointment.getProvider_address() != null){
            this.provider_address = appointment.getProvider_address();
        }
        if(appointment.getAppt_date_time() != null){
            this.appt_date_time = appointment.getAppt_date_time();
        }
    }
}
