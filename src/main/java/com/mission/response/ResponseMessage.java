package com.mission.response;

import java.util.Map;

public class ResponseMessage {

	private String message;
	
	private Map<String, String>map;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseMessage(String message) {
		
		this.message = message;
	}

	public ResponseMessage() {
		
	}
	public ResponseMessage(Map<String, String>map) {
		this.map  = map;
	}
	
	
}
