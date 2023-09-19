package com.calculator.exceptions;

import org.springframework.http.HttpStatus;

public class ControllerException extends BaseException{

	private static final long serialVersionUID = 292582097760182686L;

	public ControllerException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);		
	}
	
	public ControllerException(ServiceException e) {
		super(e.getMessage(),e.getHttpStatus());		
	}

}
