package com.mission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.MissionObjectif;

@Repository
public interface MissionObjectifDao extends JpaRepository<MissionObjectif, Integer> {

}
