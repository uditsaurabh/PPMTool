package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProjectIdException.class)
	public final ResponseEntity<?> handleProjectIDException(ProjectIdException ex, WebRequest req) {
		return new ResponseEntity(new ProjectIdExceptionResponse("Project Id cannot be duplicate"),
				HttpStatus.BAD_REQUEST);
	}

}
