package com.finalcapstoneproject.memberServicePanelApp.services;

import com.finalcapstoneproject.memberServicePanelApp.dtos.AppointmentDto;
import com.finalcapstoneproject.memberServicePanelApp.entities.Appointment;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import com.finalcapstoneproject.memberServicePanelApp.repositories.AppointmentRepository;
import com.finalcapstoneproject.memberServicePanelApp.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    //adding an appt
    @Override
    @Transactional
    public void addAppt(AppointmentDto appointmentDto, Long memberId){
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Appointment appointment = new Appointment(appointmentDto);
        if(memberOptional.isPresent()){
            appointment.setMember(memberOptional.get());
        }
        appointmentRepository.saveAndFlush(appointment);
    }
    //delete an appt
    @Override
    @Transactional
    public void deleteApptById(Long appointmentId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        appointmentOptional.ifPresent(appointment -> appointmentRepository.delete(appointment));
    }
    //update an appt
    @Override
    @Transactional
    public void updateApptById(AppointmentDto appointmentDto){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getAppt_id());
        appointmentOptional.ifPresent(appointment -> {
            appointment.setProvider_name(appointmentDto.getProvider_name());
            appointment.setProvider_address(appointmentDto.getProvider_address());
            appointment.setAppt_date_time(appointmentDto.getAppt_date_time());
            appointmentRepository.saveAndFlush(appointment);
        });
    }
    //get all appt
    @Override
    public List<AppointmentDto> getAllApptById(Long memberId){
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            List<Appointment> appointmentList = appointmentRepository.findAllByMemberEquals(memberOptional.get());
            return appointmentList.stream().map(appointment -> new AppointmentDto(appointment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    //get a single appt by appt id
    @Override
    public Optional<AppointmentDto> getApptById(Long appointmentId){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(appointmentOptional.isPresent()){
            return Optional.of(new AppointmentDto(appointmentOptional.get()));
        }
        return Optional.empty();
    }
}
