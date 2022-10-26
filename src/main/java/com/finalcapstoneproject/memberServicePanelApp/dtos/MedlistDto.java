package com.finalcapstoneproject.memberServicePanelApp.dtos;

import com.finalcapstoneproject.memberServicePanelApp.entities.Medlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedlistDto implements Serializable {
    private Long id;
    private String med_name;
    private String instruction;
    private MemberDto memberDto;

    public MedlistDto(Medlist medlist){
        if(medlist.getId() != null){
            this.id = medlist.getId();
        }
        if(medlist.getMed_name() != null){
            this.med_name = medlist.getMed_name();
        }
        if(medlist.getInstruction() != null){
            this.instruction = medlist.getInstruction();
        }
    }
}
