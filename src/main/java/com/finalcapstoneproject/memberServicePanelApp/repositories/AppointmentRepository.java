package com.finalcapstoneproject.memberServicePanelApp.repositories;

import com.finalcapstoneproject.memberServicePanelApp.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
