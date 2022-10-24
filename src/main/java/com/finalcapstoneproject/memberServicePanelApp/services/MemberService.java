package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.MemberDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberService {
    @Transactional
    List<String> addMember(MemberDto memberDto);

    List<String> memberLogin(MemberDto memberDto);
}
