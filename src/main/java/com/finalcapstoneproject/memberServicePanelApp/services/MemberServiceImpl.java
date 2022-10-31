package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.MemberDto;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import com.finalcapstoneproject.memberServicePanelApp.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //1st method, register a member

    @Override
    @Transactional
    public List<String> addMember(MemberDto memberDto){
        List<String> response = new ArrayList<>();
        Member member = new Member(memberDto);
        memberRepository.saveAndFlush(member);
        response.add("http://localhost:8080/home.html");
        return response;
    }

    //2nd method, login a member

    @Override
    public List<String> memberLogin(MemberDto memberDto){
        List<String> response = new ArrayList<>();
        Optional<Member> memberOptional = memberRepository.findByUsername(memberDto.getUsername());
        if(memberOptional.isPresent()){
            if(passwordEncoder.matches(memberDto.getPassword(), memberOptional.get().getPassword())){
                response.add("http://localhost:8080/dashboard.html");
                //line 44: add member id to the response list, the front end can use the id if needed.
                response.add(String.valueOf(memberOptional.get().getId()));
            }else{
                response.add("username or password incorrect");
            }
        }else{
            response.add("username or password incorrect");
        }
        return response;
    }

}
