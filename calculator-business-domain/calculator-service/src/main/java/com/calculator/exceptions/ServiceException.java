package com.calculator.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceException extends BaseException{

	private static final long serialVersionUID = 8458608534179326058L;
	
	
	public ServiceException(RemoteException e) {
		super(e.getMessage(), e.getHttpStatus());
	}
	
	public ServiceException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);		
	}
}
