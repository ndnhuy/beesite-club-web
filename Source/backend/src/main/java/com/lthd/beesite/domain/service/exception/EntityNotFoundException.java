package com.lthd.beesite.domain.service.exception;

import org.springframework.http.HttpStatus;


public class EntityNotFoundException extends AppException {

	public EntityNotFoundException(String userMessage, String developerMessage) {
		super(HttpStatus.NOT_FOUND.value(), 
				userMessage, 
				developerMessage);
	}
	public EntityNotFoundException(String userMessage, String developerMessage, Exception e) {
		super(HttpStatus.NOT_FOUND.value(), 
				userMessage, 
				developerMessage, 
				e);
	}
	
}
