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
    private Long med_list_id;
    private String med_name;
    private String instruction;
    private MemberDto memberDto;

    public MedlistDto(Medlist medlist){
        if(medlist.getMed_list_id() != null){
            this.med_list_id = medlist.getMed_list_id();
        }
        if(medlist.getMed_name() != null){
            this.med_name = medlist.getMed_name();
        }
        if(medlist.getInstruction() != null){
            this.instruction = medlist.getInstruction();
        }
    }
}
