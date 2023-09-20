package com.calculator.exceptions;

import org.springframework.http.HttpStatus;

public class GatewayException extends Exception {

	private static final long serialVersionUID = -8508101767595367664L;

	HttpStatus httpStatus;
	
	public GatewayException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

}
