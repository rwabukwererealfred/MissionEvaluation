package com.mission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mission.domain.Employee;
import com.mission.domain.Fundings;
import com.mission.domain.Mission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.Mission.missionStatus;
import com.mission.response.ResponseMessage;
import com.mission.service.IRegistrationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/mission/registration/")
public class MissionRegistratioController extends com.mission.response.Exception {
	
	@Autowired
	private IRegistrationService registrationService;
	
	

	@SuppressWarnings("rawtypes")
	@PostMapping(value="employeeRegistration")
	public ResponseEntity employeeRegistration(@Valid @RequestBody Employee employee) {
		try {
			registrationService.registerEmployee(employee);
		return ResponseEntity.ok(new ResponseMessage("well successfull saved"));	
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="missionCreation")
	public ResponseEntity createMission( @Valid @RequestBody Mission mission) {
		try {
			
			if(mission.getStartDate().before( mission.getEndDate())) {
			registrationService.createMission(mission);
			return ResponseEntity.ok(new ResponseMessage("well successfull created"));
			}else {
				return ResponseEntity.badRequest().body(new ResponseMessage("start date must be before end date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="createFundings")
	public ResponseEntity createfunds(@RequestBody HashMap<String, Object>map) {
		try {
			Fundings funds = new ObjectMapper().convertValue(map.get("fundings"), Fundings.class);
			int id = (Integer)map.get("missionId");
			registrationService.createFunds(funds, id);
			return ResponseEntity.ok(new ResponseMessage("funds are well successfull created"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
		}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="createMissionDetails")
	public ResponseEntity createMissionDetails(@RequestBody HashMap<String, Object>map) {
		try {
			MissionObjectif details = new ObjectMapper().convertValue(map.get("details"), MissionObjectif.class);
			int id = (Integer)map.get("missionId");
			registrationService.createMissionObjectif(details, id);
			return ResponseEntity.ok(new ResponseMessage("funds are well successfull created"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	
	
	@GetMapping(value="employeeList")
	public List<Employee>employeeList(){
		return registrationService.employeeList();
	}
	
	@GetMapping(value="missionFundings/{missionId}")
	public List<Fundings>missionFunds(@PathVariable("missionId")int missionId){
		return registrationService.missionFundsList(missionId);
	}
	
	@GetMapping(value="missionDetails/{missionId}")
	public List<MissionObjectif>missionDetails(@PathVariable("missionId")int missionId){
		return registrationService.missionObjectifList(missionId);
	}
	
	@GetMapping(value="newMission")
	public List<Mission>newMissionList(){
		return registrationService.missionList().stream().filter(i->i.isStatus() == true && i.getmStatus().equals(missionStatus.Pending)).collect(Collectors.toList());
	}
	@GetMapping(value="missionExperied")
	public List<Mission>missionExperied(){
		return registrationService.missionList().stream().filter(i->i.isStatus() == false).collect(Collectors.toList());
	}
	@GetMapping(value="missionStarted")
	public List<Mission>missionStarted(){
		return registrationService.missionList().stream().filter(i->i.getmStatus()== missionStatus.Start).collect(Collectors.toList());
	}
	
}
