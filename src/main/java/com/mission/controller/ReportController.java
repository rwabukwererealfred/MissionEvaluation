package com.mission.controller;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mission.service.ReportService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/mission/report/")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "missionReport", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> missionReport(@RequestParam("startDate")String startDate, @RequestParam("endDate")String endDate) {
		
		try {
			System.out.println("start---------------------------------------------- "+endDate);
			Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        ByteArrayInputStream bis = reportService.printInvoice(sDate, eDate);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=missionReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	@RequestMapping(value = "employeeReport", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport(@RequestParam("missionId")String missionId) {
		System.out.println("result: "+ missionId);
		try {
		
        ByteArrayInputStream bis = reportService.employess(Integer.valueOf(missionId));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employeeReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
}
