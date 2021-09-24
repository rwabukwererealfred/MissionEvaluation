package com.mission.response;

import org.springframework.web.bind.annotation.*;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;


public class Exception {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        List<String>errorMessage = new ArrayList<>();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(new ResponseMessage(errorMessage+""));
    }
	
}
