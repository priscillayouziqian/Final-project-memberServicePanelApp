package com.finalcapstoneproject.memberServicePanelApp.repositories;

import com.finalcapstoneproject.memberServicePanelApp.entities.Appointment;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByMemberEquals(Member member);
}
