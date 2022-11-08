package com.finalcapstoneproject.memberServicePanelApp.entities;

import com.fasterxml.jackson.annotation.*;
import com.finalcapstoneproject.memberServicePanelApp.dtos.AppointmentDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Appointment")
//@Data
@Setter
@Getter
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:SSSZ", shape = JsonFormat.Shape.STRING)
    private LocalDateTime appt_date_time;

    @ManyToOne
    @JsonBackReference
    private Member member;

    public List<Transportation> getTransportationList() {
        if (transportationList == null){
            transportationList = new ArrayList<>();
        }
        return transportationList;
    }

    public void addTransportation(Transportation transportation){
        this.getTransportationList().add(transportation);
        transportation.setAppointment(this);
    }
    @JsonIgnore
//    @OneToOne(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonManagedReference
//    @JoinColumn(name = "transportation_id", referencedColumnName = "id")
//    @JsonBackReference

    private List<Transportation> transportationList;

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
