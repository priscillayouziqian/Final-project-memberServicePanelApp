package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.MedlistDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MedlistService {
    @Transactional
    List<String> addMedlist(MedlistDto medlistDto, Long userId);

    //delete a med list
    @Transactional
    void deleteMedlistById(Long medlistId);

    //update a med list
    @Transactional
    void updateMedlistById(MedlistDto medlistDto);

    //get all med list
    List<MedlistDto> getAllMedlistById(Long memberId);

    //get a med list by the medlist id
    Optional<MedlistDto> getMedlistById(Long medlistId);
}
