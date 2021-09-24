package com.mission.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mission.dao.EmployeeMissionDao;
import com.mission.dao.MissionDao;
import com.mission.dao.MissionObjectifDao;
import com.mission.dao.MissionReportDao;
import com.mission.dao.TraineeCommentDao;
import com.mission.dao.TraineeDao;
import com.mission.domain.EmployeeMission;
import com.mission.domain.Mission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.MissionReport;
import com.mission.domain.Trainee;
import com.mission.domain.TraineeComment;

@Service
public class MissionServiceListImpl implements IMissionServiceList {
	
	@Autowired
	private EmployeeMissionDao employeeMissionDao;
	
	@Autowired
	private MissionObjectifDao objectDao;
	
	@Autowired
	private MissionReportDao missionReportDao;
	
	@Autowired
	private MissionDao missionDao;
	
	
	@Autowired
	private TraineeDao traineeDao;
	
	@Autowired
	private TraineeCommentDao traineeCommnentDao;

	@Override
	public List<EmployeeMission> MyAvailableMission(String employeeId, boolean missionStatus) {
		List<EmployeeMission>list = employeeMissionDao.findAll().stream().filter(i->i.getEmployee().getNatinalityID().equals(employeeId) && 
				i.getMission().isStatus() == missionStatus).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<MissionObjectif> missionObjectifList(int missionId) {
		
		return objectDao.findAll().stream().filter(i->i.getMission().getId() == missionId).collect(Collectors.toList());
				
	}

	@Override
	public EmployeeMission findMissionById(int missionId) {
		EmployeeMission m = employeeMissionDao.findById(missionId).get();
		return m;
	}

	@Override
	public List<MissionReport> missionReportByEmployee(int employeeMission) {
		return missionReportDao.findAll().stream().filter(i->i.getEmployeeMission().getId() == employeeMission).collect(Collectors.toList());
	}

	@Override
	public List<MissionReport> missionReportedByToMe(int employeeMission) {
		// TODO Auto-generated method stub
		EmployeeMission m = employeeMissionDao.findById(employeeMission).get();
		return missionReportDao.findAll().stream().filter(i->i.getEmployeeMission().getMission().getId() == m.getMission().getId()).collect(Collectors.toList());
	}

	@Override
	public List<Trainee> traineeList(int missionId) {
		Mission mission = missionDao.getOne(missionId);
		return traineeDao.findByMission(mission);
	}

	@Override
	public List<TraineeComment> traineeCommentList(int missionId) {
		Mission mission = missionDao.getOne(missionId);
		return traineeCommnentDao.findByMission(mission);
	}

}
