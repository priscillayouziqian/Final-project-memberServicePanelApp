package com.finalcapstoneproject.memberServicePanelApp.controllers;

import com.finalcapstoneproject.memberServicePanelApp.dtos.MedlistDto;
import com.finalcapstoneproject.memberServicePanelApp.services.MedlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/medlist")
public class MedlistController {
    @Autowired
    private MedlistService medlistService;

    //get all medlist by member id
    @GetMapping("/member/{memberId}")
    public List<MedlistDto> getMedlistByMember(@PathVariable Long memberId){
        return medlistService.getAllMedlistById(memberId);
    }
    //add a new medlist
    @PostMapping("/member/{memberId}")
    public List<String> addMedlist(@RequestBody MedlistDto medlistDto,@PathVariable Long memberId){
        return medlistService.addMedlist(medlistDto, memberId);
    }
    //delete a medlist
    @DeleteMapping("/{medlistId}")
    public void deleteMedlistById(@PathVariable Long medlistId){
        medlistService.deleteMedlistById(medlistId);
    }
    //update an existing Medlist
    @PutMapping
    public void updateMedlist(@RequestBody MedlistDto medlistDto){
        medlistService.updateMedlistById(medlistDto);
    }
    //get a medlist by medlist's id
    @GetMapping("/{medlistId}")
    public Optional<MedlistDto> getMedlistById(@PathVariable Long medlistId){
        return medlistService.getMedlistById(medlistId);
    }
}
