package com.mission.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mission.dao.EmployeeDao;
import com.mission.dao.FundingsDao;
import com.mission.dao.MissionDao;
import com.mission.dao.MissionObjectifDao;
import com.mission.dao.RoleDao;
import com.mission.dao.TraineeDao;
import com.mission.domain.Employee;
import com.mission.domain.Fundings;
import com.mission.domain.Mission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.Role;
import com.mission.domain.Mission.missionStatus;
import com.mission.domain.Role.ERole;
import com.mission.domain.Trainee;

@Service
public class RegistrationImpl implements IRegistrationService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private FundingsDao fundDao;
	
	@Autowired
	private MissionDao missionDao;
	
	@Autowired
	private MissionObjectifDao objectDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private TraineeDao traineeDao;

	@Override
	public void registerEmployee(Employee employee) {
		Optional<Role>r = roleDao.findAll().stream().filter(i->i.getName().equals(employee.getRole().getName())).findAny();
		employee.setRole(r.get());
		employee.setAccess(true);
		employeeDao.save(employee);
	}

	@Override
	public void createMission(Mission mission) {
		mission.setCreatedDate(new Date());
		mission.setStatus(true);
		mission.setmStatus(missionStatus.Pending);
		missionDao.save(mission);
		
	}

	@Override
	public void createMissionObjectif(MissionObjectif objectif, int missionId) {
		Mission mission = missionDao.getOne(missionId);
		objectif.setMission(mission);
		objectDao.save(objectif);
		
	}

	@Override
	public List<Employee> employeeList() {
		return employeeDao.findAll();
	}

	@Override
	public List<Mission> missionList() {
		
		return missionDao.findAll();
	}

	@Override
	public List<MissionObjectif> missionObjectifList(int missionId) {
		return objectDao.findAll().stream().filter(i->i.getMission().getId() == missionId).collect(Collectors.toList());
	}

	@Override
	public void createFunds(Fundings fundings, int missionId) {
	Mission mission = missionDao.getOne(missionId);
	fundings.setMission(mission);
	fundDao.save(fundings);
		
	}

	@Override
	public List<Fundings> missionFundsList(int missionId) {
		
		return fundDao.findAll().stream().filter(i->i.getMission().getId() == missionId).collect(Collectors.toList());
	}

	@Override
	public void traineeRegistration(int missionId,Trainee trainee) {
		Optional<Mission>mission = missionDao.findById(missionId);
		if(mission.isPresent() == false) {
			throw new RuntimeException("mission does not exist");
		}else {
			trainee.setMission(mission.get());
			traineeDao.save(trainee);
		}
		
	}

}
