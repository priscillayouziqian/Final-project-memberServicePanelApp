package com.finalcapstoneproject.memberServicePanelApp.repositories;

import com.finalcapstoneproject.memberServicePanelApp.entities.Medlist;
import com.finalcapstoneproject.memberServicePanelApp.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedlistRepository extends JpaRepository<Medlist, Long> {
    List<Medlist> findAllByIdEquals(Member member);
}
