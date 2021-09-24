package com.mission.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mission.dao.EmployeeMissionDao;
import com.mission.dao.MissionDao;
import com.mission.domain.EmployeeMission;
import com.mission.domain.Mission;

@Service
public class ReportService {
	
	@Autowired
	private  MissionDao missionDao;
	
	@Autowired
	private EmployeeMissionDao employeeMissionDao;

	public ByteArrayInputStream printInvoice(Date startDate, Date endDate) {

		List<Mission>list = missionDao.findAll().stream().filter(i->startDate.before(i.getStartDate()) && endDate.after(i.getStartDate()) 
				).collect(Collectors.toList());
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			LineSeparator ls = new LineSeparator();
			String start = new SimpleDateFormat("dd-MMM-yyyy").format(startDate);

			String end = new SimpleDateFormat("dd-MMM-yyyy").format(endDate);
			document.add(new Paragraph(
					"Mission Evaluation System \nEmail : evaluation@gmail.com \nDate Started :" +
			start+"\nEnd Date :"+ end));
			document.add(new Paragraph("                                          "));
			
			Paragraph p = new Paragraph("Mission Report Details",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.NORMAL, BaseColor.DARK_GRAY));
			p.setAlignment(Element.ALIGN_CENTER);

			document.add(p);
			document.add(new Chunk(ls));
			document.add(new Paragraph("                                          "));
			
			
			PdfPTable table = new PdfPTable(6);

			table.setWidthPercentage(100);
			
			

			BaseColor color = new BaseColor(10, 113, 110);
			Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.WHITE);

			PdfPCell driverName = new PdfPCell(new Phrase("Mission Title\n", font0));
			driverName.setBackgroundColor(color);
			table.addCell(driverName);
			
			PdfPCell rider = new PdfPCell(new Phrase("Mission Description\n", font0));
			rider.setBackgroundColor(color);
			table.addCell(rider);
			
			PdfPCell startPlace = new PdfPCell(new Phrase("Start Date\n", font0));
			startPlace.setBackgroundColor(color);
			table.addCell(startPlace);
			
			PdfPCell endPlace = new PdfPCell(new Phrase("End Date\n", font0));
			endPlace.setBackgroundColor(color);
			table.addCell(endPlace);
			
			PdfPCell kilo = new PdfPCell(new Phrase("Status\n", font0));
			kilo.setBackgroundColor(color);
			table.addCell(kilo);
			
			PdfPCell evaluation = new PdfPCell(new Phrase("Evaluation Result\n", font0));
			evaluation.setBackgroundColor(color);
			table.addCell(evaluation);
			
			for(Mission m : list) {
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(m.getTitle()));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(m.getDescription()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd-MMM-YYYY").format(m.getStartDate())));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd-MMM-YYYY").format(m.getEndDate())));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(m.getmStatus().toString()));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				if(m.getEvaluationPercentage() ==0) {
					Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.DARK_GRAY);
					cell = new PdfPCell(new Phrase("Still Pending", font1));
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}else {
				if(m.getEvaluationPercentage()>80) {
					Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLUE);
					cell = new PdfPCell(new Phrase("Excellent", font1));
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					
					table.addCell(cell);
				}else if(m.getEvaluationPercentage()<=80 && m.getEvaluationPercentage()>=65) {
					Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.GREEN);
					cell = new PdfPCell(new Phrase("Very Good", font1));
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}else if(m.getEvaluationPercentage()<=64 && m.getEvaluationPercentage()>=50) {
					Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.ORANGE);
					cell = new PdfPCell(new Phrase("Good", font1));
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}else {
					Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.RED);
					cell = new PdfPCell(new Phrase("Fair", font1));
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}
				}
				
				
			}
				
			
				document.add(table);
				
				document.add(new Paragraph("                                          "));
				document.add(new Paragraph("                                          "));
				String dd = new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(new Date());
				Paragraph printedOn = new Paragraph("Printed On:" + dd);
				printedOn.setAlignment(Element.ALIGN_RIGHT);
				document.add(printedOn);
				document.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	public ByteArrayInputStream employess(int missionId) {
		
		Mission mission = missionDao.getOne(missionId);

		List<EmployeeMission>list = employeeMissionDao.findAll().stream().filter(i->i.getMission().getId() == mission.getId()).collect(Collectors.toList());
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			LineSeparator ls = new LineSeparator();
			String start = new SimpleDateFormat("dd-MMM-yyyy").format(mission.getStartDate());

			String end = new SimpleDateFormat("dd-MMM-yyyy").format(mission.getEndDate());
			document.add(new Paragraph(
					"Mission Evaluation System \nEmail : evaluation@gmail.com \nDate Started :" +
			start+"\nEnd Date :"+ end));
			document.add(new Paragraph("                                          "));
			
			Paragraph p = new Paragraph("Employees Attended To "+mission.getTitle()+" Mission",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.NORMAL, BaseColor.DARK_GRAY));
			p.setAlignment(Element.ALIGN_CENTER);

			document.add(p);
			document.add(new Chunk(ls));
			document.add(new Paragraph("                                          "));
			
			
			PdfPTable table = new PdfPTable(5);

			table.setWidthPercentage(100);
			
			

			BaseColor color = new BaseColor(10, 113, 110);
			Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.WHITE);

			PdfPCell driverName = new PdfPCell(new Phrase("Names\n", font0));
			driverName.setBackgroundColor(color);
			table.addCell(driverName);
			
			PdfPCell rider = new PdfPCell(new Phrase("National Id\n", font0));
			rider.setBackgroundColor(color);
			table.addCell(rider);
			
			PdfPCell startPlace = new PdfPCell(new Phrase("Phone Number\n", font0));
			startPlace.setBackgroundColor(color);
			table.addCell(startPlace);
			
			PdfPCell endPlace = new PdfPCell(new Phrase("Date Of Birth\n", font0));
			endPlace.setBackgroundColor(color);
			table.addCell(endPlace);
			
			PdfPCell kilo = new PdfPCell(new Phrase("Email\n", font0));
			kilo.setBackgroundColor(color);
			table.addCell(kilo);
			
			for(EmployeeMission m : list) {
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(m.getEmployee().getFirstName()+" "+m.getEmployee().getLastName()));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(m.getEmployee().getNatinalityID()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(m.getEmployee().getPhoneNumber()));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd-MMM-YYYY").format(m.getEmployee().getDateofbirth())));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(m.getEmployee().getEmail()));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
				
			
				document.add(table);
				
				document.add(new Paragraph("                                          "));
				document.add(new Paragraph("                                          "));
				String dd = new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(new Date());
				Paragraph printedOn = new Paragraph("Printed On:" + dd);
				printedOn.setAlignment(Element.ALIGN_RIGHT);
				document.add(printedOn);
				document.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
