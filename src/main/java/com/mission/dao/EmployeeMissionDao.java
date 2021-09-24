package com.mission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.EmployeeMission;

@Repository
public interface EmployeeMissionDao extends JpaRepository<EmployeeMission, Integer> {

}
