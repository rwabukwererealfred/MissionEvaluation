package com.mission.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.mission.domain.Employee;


public class MessageService {
	private final String sender = "mtnrwandaresponse@gmail.com";
	private final String password = "fred18404";

	public void sendMessage(Employee employee) {
		try {
			

				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(
						"<h1 style=font-weight: bold; color: maroon;><center>RWANDA GOVERMENT BOARD </center></h1></center><br />");
				stringBuilder.append("Hello ,"  +employee.getFirstName()+" "+employee.getLastName()  + "<br />");
				stringBuilder.append(
						"Kindly, we want to inform you that you have new mission :<br /><br />");
				
				stringBuilder.append("<b>Please Go and check to our application for more details.</b>&nbsp;&nbsp;<br />");
				stringBuilder.append("<br /><br /> Thank you!!");
				String emailMessage = stringBuilder.toString();

				// Assuming you are sending email through relay.jangosmtp.net
				String host = "smtp.gmail.com";

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "587");

				// Get the Session object.
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender, password);
					}
				});

				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(sender));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employee.getEmail()));

				// Set Subject: header field
				message.setSubject("Mission Notification");

				// Now set the actual message

				message.setContent(emailMessage, "text/html");
				// Send message
				Transport.send(message);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
		}
		
}
}
