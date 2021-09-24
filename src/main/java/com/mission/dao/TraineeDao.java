package com.mission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.Mission;
import com.mission.domain.Trainee;

@Repository
public interface TraineeDao extends JpaRepository<Trainee, String> {

	
	public List<Trainee>findByMission(Mission mission);
}
