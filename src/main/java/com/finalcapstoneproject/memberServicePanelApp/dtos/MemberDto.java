package com.finalcapstoneproject.memberServicePanelApp.dtos;

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
    private Long id;
    private String username;
    private String password;
    private Set<MedlistDto> medlistDtoSet = new HashSet<>();
    private Set<AppointmentDto> appointmentDtoSet = new HashSet<>();

    public MemberDto(Member member){
        if(member.getId() != null){
            this.id = member.getId();
        }
        if(member.getUsername() != null){
            this.username = member.getUsername();
        }
        if(member.getPassword() != null){
            this.password = member.getPassword();
        }
    }
}
