package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {

	public ResponseEntity<?> mapValidationService(BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> error = new HashMap<>();
			for (FieldError e : result.getFieldErrors()) {
				error.put(e.getField(), e.getDefaultMessage());
			}
			return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
