package com.calculator.exceptions;

import org.springframework.http.HttpStatus;

class BaseException extends Exception{

	private static final long serialVersionUID = -3630412074138638096L;
	
	
	
	private boolean suppressStacktrace = true;

   

    @Override
    public String toString() {
        if (suppressStacktrace) {
            return getLocalizedMessage();
        } else {
            return super.toString();
        }
    }
	
	
	
	
	
	
    private HttpStatus httpStatus;
    
    public BaseException(String message,HttpStatus httpStatus) {
        
    	
    	super(message, null, true, false);
    	
        this.httpStatus = httpStatus;
    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
    
    

   	
	
	
	

		
	
}
