package com.mission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.MissionReport;

@Repository
public interface MissionReportDao extends JpaRepository<MissionReport, Integer> {

}
