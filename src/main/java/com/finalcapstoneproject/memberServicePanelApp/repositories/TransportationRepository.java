package com.finalcapstoneproject.memberServicePanelApp.repositories;

import com.finalcapstoneproject.memberServicePanelApp.entities.Appointment;
import com.finalcapstoneproject.memberServicePanelApp.entities.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {
    List<Transportation> findAllByAppointmentEquals(Appointment appointment);
}
