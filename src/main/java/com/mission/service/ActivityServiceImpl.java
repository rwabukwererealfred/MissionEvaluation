package com.mission.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mission.config.UserNotFoundException;
import com.mission.dao.EmployeeDao;
import com.mission.dao.EmployeeMissionDao;
import com.mission.dao.FundingsDao;
import com.mission.dao.MissionDao;
import com.mission.dao.MissionObjectifDao;
import com.mission.dao.MissionReportDao;
import com.mission.dao.TraineeCommentDao;
import com.mission.dao.TraineeDao;
import com.mission.domain.Employee;
import com.mission.domain.EmployeeMission;
import com.mission.domain.EmployeeMission.Status;
import com.mission.domain.Fundings;
import com.mission.domain.Mission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.MissionReport;
import com.mission.domain.Trainee;
import com.mission.domain.TraineeComment;

@Service
public class ActivityServiceImpl implements IActivityService {
	
	@Autowired
	private EmployeeMissionDao employeeMissionDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private MissionDao missionDao;
	@Autowired
	private FundingsDao fundingsDao;
	@Autowired
	private MissionObjectifDao objDao;
	
	@Autowired
	private MissionReportDao reportDao;
	
	@Autowired
	private TraineeDao traineeDao;
	
	@Autowired
	private TraineeCommentDao traineeCommentDao;

	@Override
	public void assignMissionToEmployee(String employeeId, int missionId) {
		
		EmployeeMission empMission = new EmployeeMission();
		Employee employee = employeeDao.getOne(employeeId);
		Mission mission = missionDao.getOne(missionId);
		empMission.setAssignedDate(new Date());
		empMission.setMission(mission);
		empMission.setEmployee(employee);
		empMission.setStatus(Status.NotAttend);
		employeeMissionDao.save(empMission);
	}

	@Override
	public List<EmployeeMission> empMissionList(int missionId) {
		
		return employeeMissionDao.findAll().stream().filter(i->i.getMission().getId() == missionId).collect(Collectors.toList());
	}

	@Override
	public Employee findEmployee(String empId) {
		
		return employeeDao.getOne(empId);
	}

	@Transactional
	@Override
	public void updateExperiedMission(int mission, double percentage) {
		
		missionDao.updateMission(mission, percentage);
	}

	@Override
	public void sendReport(MissionReport report , int employeeMissionId, String employeeId) throws Exception {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(()->new RuntimeException("Employe does not exist"));
		EmployeeMission emp = employeeMissionDao.findById(employeeMissionId).get();
		report.setEmployeeMission(emp);
		report.setEmployee(employee);
		report.setSendDate(new Date());
		reportDao.save(report);
		
	}

	@Transactional
	@Override
	public void startMission(int missionId) {
		List<EmployeeMission>employeeMission = employeeMissionDao.findAll().stream().filter(i->i.getMission().getId()==missionId).collect(Collectors.toList());
		List<Fundings>fundindsList = fundingsDao.findAll().stream().filter(i->i.getMission().getId() == missionId).collect(Collectors.toList());
		List<MissionObjectif>objectives = objDao.findAll().stream().filter(i->i.getMission().getId() == missionId).collect(Collectors.toList());
		if(employeeMission.isEmpty() ==false) {
			if(objectives.isEmpty() == false) {
				if(fundindsList.isEmpty() == false) {
					missionDao.startMission(missionId);	
				}else {
					throw new UserNotFoundException("please add fundings first before you start mission");
				}
		
			}else {
				throw new UserNotFoundException("please add mission objectif first before you start mission");
			}
		}else {
			throw new UserNotFoundException("please select employee before start mission");
		}
	}

	@Override
	public void updateMissionObject(int missionObjectId, int percentage) {
		MissionObjectif objectif = objDao.findById(missionObjectId).orElseThrow(()->new RuntimeException("mission objectif id is not found"));
		objectif.setPercentage(percentage);
		objDao.save(objectif);
		
	}

	@Override
	public void traineeComment(String comment, String nationalId, int missionId) {
		Optional<Trainee>trainee = traineeDao.findAll().stream().filter(i->i.getMission().getId()==missionId && i.getNationalId().equals(nationalId))
				.findAny();
		if(trainee.isPresent()) {
			TraineeComment traineeComment = new TraineeComment();
			traineeComment.setComment(comment);
			traineeComment.setTrainee(trainee.get());
			traineeComment.setMission(trainee.get().getMission());
			traineeCommentDao.save(traineeComment);
		}else {
			throw new RuntimeException("Sorry!! you are not allowed to comment to this mission");
		}
		
	}

	@Override
	public void advisorAddComment(int missionReportId, String Comment) {
		MissionReport report = reportDao.getOne(missionReportId);
		report.setAdvisorComment(Comment);
		reportDao.save(report);
		
	}

	@Override
	public void hrAddComment(int missionId, String comment) {
		Mission mission = missionDao.getOne(missionId);
		mission.setHrComment(comment);
		missionDao.save(mission);
		
	}

}
