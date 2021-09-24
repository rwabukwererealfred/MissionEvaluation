package com.mission.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mission.domain.Employee;
import com.mission.domain.EmployeeMission;
import com.mission.domain.Fundings;
import com.mission.domain.Mission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.MissionReport;
import com.mission.domain.Trainee;
import com.mission.domain.TraineeComment;
import com.mission.domain.Mission.missionStatus;
import com.mission.service.IAuthenticationService;
import com.mission.service.IMissionServiceList;
import com.mission.service.IRegistrationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/mission/missionList/")
public class MissionListController {
	
	@Autowired
	private IAuthenticationService authService;
	
	@Autowired
	private IMissionServiceList serviceList;
	
	@Autowired
	private IRegistrationService regService;

	@GetMapping("myNewAvailableList")
	public List<EmployeeMission>newAssignedMission(Principal principal){
		Employee employee = authService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("username does not exist"));
		System.out.println("new mission Id: "+ employee.getEmail());
		return serviceList.MyAvailableMission(employee.getNatinalityID(), true).stream().filter(i->i.getMission().
				getmStatus().equals(missionStatus.Start)).collect(Collectors.toList());
	}
	@GetMapping("myExperiedMissionList")
	public List<EmployeeMission>experiedAssignedMission(Principal principal){
		Employee employee = authService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("username does not exist"));
		return serviceList.MyAvailableMission(employee.getNatinalityID(), false);
	}
	
	@GetMapping(value="missionObjects/{missionId}")
	public List<MissionObjectif>missionObjectifs(@PathVariable("missionId")int missionId){
		EmployeeMission em = serviceList.findMissionById(missionId);
		return serviceList.missionObjectifList(em.getMission().getId());
	}
	@GetMapping(value="employeeMissionById/{missionId}")
	public EmployeeMission findMissionById(@PathVariable("missionId")int missionId) {
		return serviceList.findMissionById(missionId);
	}
	
	@GetMapping(value="employeeMissionReport/{employeeMissionId}")
	public List<MissionReport>missionReportByEmployee(@PathVariable("employeeMissionId")int employeeMissionId){
		return serviceList.missionReportByEmployee(employeeMissionId).stream().sorted((x,y)->y.getSendDate().compareTo(x.getSendDate())).
				collect(Collectors.toList());
	}
	@GetMapping(value="missionReportedToMe/{employeeMissionId}")
	public List<MissionReport>missionReportedToMe(@PathVariable("employeeMissionId")int employeeMissionId, Principal principal){
		Employee employee = authService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("username does not exist"));
		
		return serviceList.missionReportedByToMe(employeeMissionId).stream().filter(i->i.getEmployee().getNatinalityID().equals(employee.getNatinalityID()))
				.sorted((x,y)->y.getSendDate().compareTo(x.getSendDate())).
				collect(Collectors.toList());
	}
	
	@GetMapping(value="fundings/{employeeMissionId}")
	public List<Fundings>viewMissionFundings(@PathVariable("employeeMissionId")int employeeMissionId){
		EmployeeMission em = serviceList.findMissionById(employeeMissionId);
		return regService.missionFundsList(em.getMission().getId());
	}
	@GetMapping(value="funding/{missionId}")
	public List<Fundings>missionFundings(@PathVariable("missionId")int missionId){
		
		return regService.missionFundsList(missionId);
	}
	@GetMapping(value="objectives/{missionId}")
	public List<MissionObjectif>viewObjectives(@PathVariable("missionId")int missionId){
		return serviceList.missionObjectifList(missionId);
	}
	
	@GetMapping(value="traineeList/{missionId}")
	public List<Trainee>traineeList(@PathVariable("missionId")int missionId){
		
		return serviceList.traineeList(missionId);
	}
	
	@GetMapping(value="traineeComments/{missionId}")
	public List<TraineeComment>commentList(@PathVariable("missionId")int missionId){
		
		return serviceList.traineeCommentList(missionId);
	}
	
	
}
