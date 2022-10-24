package com.finalcapstoneproject.memberServicePanelApp.repositories;

import com.finalcapstoneproject.memberServicePanelApp.entities.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {
}
