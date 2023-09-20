package com.calculator.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception{

	private static final long serialVersionUID = -5817533333548973714L;
	
	private HttpStatus httpStatus;
    
    public BaseException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
