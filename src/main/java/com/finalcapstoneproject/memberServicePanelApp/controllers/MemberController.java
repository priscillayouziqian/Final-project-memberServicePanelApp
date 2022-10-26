package com.finalcapstoneproject.memberServicePanelApp.controllers;

import com.finalcapstoneproject.memberServicePanelApp.dtos.MemberDto;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import com.finalcapstoneproject.memberServicePanelApp.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //register a member
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public List<String> addMember(@RequestBody MemberDto memberDto){
        System.out.println("memberDto="+memberDto);
        String passHash = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(passHash);
        return memberService.addMember(memberDto);
    }
    //log in a member
    @PostMapping("/login")
    public List<String> memberLogin(@RequestBody MemberDto memberDto){
        return memberService.memberLogin(memberDto);
    }
}
