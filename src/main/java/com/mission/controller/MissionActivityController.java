package com.mission.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mission.domain.Employee;
import com.mission.domain.EmployeeMission;
import com.mission.domain.MissionObjectif;
import com.mission.domain.MissionReport;
import com.mission.domain.Trainee;
import com.mission.dto.MissionDto;
import com.mission.response.ResponseMessage;
import com.mission.service.IActivityService;
import com.mission.service.IRegistrationService;
import com.mission.service.MessageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "api/mission/activity/")
public class MissionActivityController extends com.mission.response.Exception {

	@Autowired
	private IActivityService activityService;
	
	
	@Autowired
	private IRegistrationService regService;
	
	
	
	private double percent =0.0;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "addEmployee")
	public ResponseEntity assignMissionToEmployee(@RequestBody HashMap<String, Object> map) {
		try {
			boolean checks = false;
			List<String> names = new ArrayList<>();
			int missionId = (Integer) map.get("mission");

			String emp = (String) map.get("employee");
			Employee employee = activityService.findEmployee(emp);

			if (activityService.empMissionList(missionId).isEmpty() == false) {
				for (EmployeeMission empMission : activityService.empMissionList(missionId)) {
					if (employee.getNatinalityID() == empMission.getEmployee().getNatinalityID()) {
						checks = true;
						names.add(employee.getFirstName());
						System.out.println("out put: " + checks);
					}
				}
			}

			if (checks == false) {
				System.out.println("message sent!");
				new MessageService().sendMessage(employee);
				activityService.assignMissionToEmployee(emp, missionId);
				return ResponseEntity.ok(new ResponseMessage("Well Successfull Assigned"));
			} else {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage(names + " arleady exist on mission selection"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	@SuppressWarnings("rawtypes")
	@PutMapping(value = "startMisson/{missionId}")
	public ResponseEntity updateMission(@PathVariable(name = "missionId") int missionId) {
		try {
			activityService.startMission(missionId);
			return ResponseEntity.ok(new ResponseMessage("Well Successfull started"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}

//	@SuppressWarnings("rawtypes")
//	@PutMapping(value = "closeMisson/{missionId}")
//	public ResponseEntity closeMission(@PathVariable(name = "missionId") int missionId) {
//		try {
//			activityService.updateExperiedMission(missionId);
//			return ResponseEntity.ok(new ResponseMessage("Well Successfull closed"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
//		}
//	}

	@GetMapping(value = "employeeMission/{missionId}")
	public List<EmployeeMission> missionEmployeeList(@PathVariable("missionId") int missionId) {
		return activityService.empMissionList(missionId);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "sendDailyReport")
	public ResponseEntity sendReport(@PathParam("file") MultipartFile file,
			@PathParam("employeeMissionId") int employeeMissionId, @PathParam("employeeReport") String employeeReport,
			@PathParam("employeeId") String employeeId) {
		try {
			MissionReport report = new ObjectMapper().readValue(employeeReport, MissionReport.class);

			if (file.isEmpty() == false || file != null) {
				String filename = file.getOriginalFilename();
				String modifiedFileName = System.currentTimeMillis() + "_" + filename;

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src\\main\\resources\\static\\files\\" + modifiedFileName)));
				stream.write(bytes);
				stream.close();
				report.setFile(modifiedFileName);
			}
			activityService.sendReport(report, employeeMissionId, employeeId);

			return ResponseEntity.ok(new ResponseMessage("Well Successfull Sent"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}

	@GetMapping("download/{fileName}")
	public Object getPdf(@PathVariable("fileName") String fileName) throws IOException {
		File file = new File("src\\main\\resources\\static\\files\\" + fileName);
		FileInputStream fileInputStream = new FileInputStream(file);
		return IOUtils.toByteArray(fileInputStream);
	}

	@PutMapping(value = "updatePercentage")
	public ResponseEntity<?> updatePercentageObjectif(@RequestBody List<MissionObjectif> objective) {
		this.percent =0.0;
		try {
			Optional<MissionObjectif> ob = objective.stream().filter(i -> i.getPercentage() == 0).findAny();
			if (ob.isPresent()) {
				return ResponseEntity.badRequest().body(new ResponseMessage(ob.get().getSubject() + " is not checked"));
			} else {
				objective.forEach(i -> {
					percent +=i.getPercentage();
					activityService.updateMissionObject(i.getId(), i.getPercentage());
				});
				this.percent /=objective.size();
				activityService.updateExperiedMission(objective.stream().findAny().get().getMission().getId(), this.percent);
				return ResponseEntity.ok(new ResponseMessage("Well Successfull Sent"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	
	@PostMapping(value="registerTrainee")
	public ResponseEntity<?>registerTrainee(@RequestBody HashMap<String, Object>map){
		try {
			ObjectMapper mapper = new ObjectMapper();
			Integer missionId = (Integer)map.get("missionId");
			
			Trainee trainee = mapper.convertValue(map.get("trainee"), Trainee.class);
			this.regService.traineeRegistration(missionId, trainee);
			return ResponseEntity.ok(new ResponseMessage("Well Successfull Registered"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	
	
	@PostMapping(value="traineeSendComment")
	public ResponseEntity<?>traineeComment(@RequestBody HashMap<String, Object>map){
		try {
		
			String missionId = (String)map.get("missionId");
			System.out.println("missionID: "+missionId);
			String comment =(String)map.get("comment");
			System.out.println("comment : "+comment);
			String nationalId =(String)map.get("nationalId");
			System.out.println("NATIONAL ID : "+nationalId);
			this.activityService.traineeComment(comment, nationalId,Integer.valueOf(missionId));
			return ResponseEntity.ok(new ResponseMessage("Well Successfull sent"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	
	@PutMapping("hrAddComment")
	public ResponseEntity<?>hrAddCommentToMission(@RequestBody MissionDto missionDto){
		try {
			System.out.println(missionDto.getMissionId()+"  "+ missionDto.getComment());
			this.activityService.hrAddComment(missionDto.getMissionId(),missionDto.getComment());
			
			return ResponseEntity.ok(new ResponseMessage("Well Successfull added"));
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	@PutMapping("advisorAddComment")
	public ResponseEntity<?>advisorAddCommentToMission(@RequestBody MissionDto missionDto){
		try {
			this.activityService.advisorAddComment(missionDto.getMissionId(),missionDto.getComment());
			
			return ResponseEntity.ok(new ResponseMessage("Well Successfull added"));
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
}
