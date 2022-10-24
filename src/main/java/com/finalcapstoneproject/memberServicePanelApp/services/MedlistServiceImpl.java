package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.MedlistDto;
import com.finalcapstoneproject.memberServicePanelApp.entities.Medlist;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import com.finalcapstoneproject.memberServicePanelApp.repositories.MedlistRepository;
import com.finalcapstoneproject.memberServicePanelApp.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedlistServiceImpl implements MedlistService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MedlistRepository medlistRepository;

    //adding a med list

    @Override
    @Transactional
    public void addMedlist(MedlistDto medlistDto, Long userId){
        Optional<Member> memberOptional = memberRepository.findById(userId);
        Medlist medlist = new Medlist(medlistDto);
        if(memberOptional.isPresent()){
            medlist.setMember(memberOptional.get());
        }
        medlistRepository.saveAndFlush(medlist);
    }
    //delete a med list
    @Override
    @Transactional
    public void deleteMedlistById(Long medlistId){
        Optional<Medlist> medlistOptional = medlistRepository.findById(medlistId);
        medlistOptional.ifPresent(medlist -> medlistRepository.delete(medlist));
    }
    //update a med list
    @Override
    @Transactional
    public void updateMedlistById(MedlistDto medlistDto){
        Optional<Medlist> medlistOptional = medlistRepository.findById(medlistDto.getMed_list_id());
        medlistOptional.ifPresent(medlist -> {
            medlist.setMed_name(medlistDto.getMed_name());
            medlist.setInstruction(medlistDto.getInstruction());
            medlistRepository.saveAndFlush(medlist);
        });
    }
    //get all med list
    @Override
    public List<MedlistDto> getAllMedlistById(Long memberId){
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if(memberOptional.isPresent()){
            List<Medlist> medlistList = medlistRepository.findAllByIdEquals(memberOptional.get());
            return medlistList.stream().map(medlist -> new MedlistDto(medlist)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    //get a med list by the medlist id
    @Override
    public Optional<MedlistDto> getMedlistById(Long medlistId){
        Optional<Medlist> medlistOptional = medlistRepository.findById(medlistId);
        if(medlistOptional.isPresent()){
            return Optional.of(new MedlistDto(medlistOptional.get()));
        }
        return Optional.empty();
    }
}
