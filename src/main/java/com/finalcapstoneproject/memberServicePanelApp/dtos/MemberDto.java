package com.finalcapstoneproject.memberServicePanelApp.dtos;

import com.finalcapstoneproject.memberServicePanelApp.entities.Appointment;
import com.finalcapstoneproject.memberServicePanelApp.entities.Medlist;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto implements Serializable {
    private Long member_id;
    private String username;
    private String password;
    private Set<MedlistDto> medlistDtoSet = new HashSet<>();
    private Set<AppointmentDto> appointmentDtoSet = new HashSet<>();

    public MemberDto(Member member){
        if(member.getMember_id() != null){
            this.member_id = member.getMember_id();
        }
        if(member.getUsername() != null){
            this.username = member.getUsername();
        }
        if(member.getPassword() != null){
            this.password = member.getPassword();
        }
    }
}
