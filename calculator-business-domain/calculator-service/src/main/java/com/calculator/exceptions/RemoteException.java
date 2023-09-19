package com.calculator.exceptions;

import org.springframework.http.HttpStatus;


public class RemoteException extends BaseException{

	private static final long serialVersionUID = 4356306668063020114L;

	public RemoteException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);		
	}

	

}
