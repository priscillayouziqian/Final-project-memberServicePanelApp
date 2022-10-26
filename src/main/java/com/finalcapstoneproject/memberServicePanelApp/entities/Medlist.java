package com.finalcapstoneproject.memberServicePanelApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.finalcapstoneproject.memberServicePanelApp.dtos.MedlistDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MedList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String med_name;

    @Column(columnDefinition = "text")
    private String instruction;

    @ManyToOne
    @JsonBackReference
    private Member member;

    public Medlist(MedlistDto medlistDto){
        if(medlistDto.getMed_name() != null){
            this.med_name = medlistDto.getMed_name();
        }
        if(medlistDto.getInstruction() != null){
            this.instruction = medlistDto.getInstruction();
        }
    }
}
