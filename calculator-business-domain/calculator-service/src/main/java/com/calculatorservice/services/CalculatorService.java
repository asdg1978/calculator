
package com.calculatorservice.services;

import java.util.List;

import com.calculator.exceptions.ServiceException;
import com.calculatorservice.entities.Operation;

public interface CalculatorService {
    
	public List<Operation> calculateAddingPercentage(Double paramA,Double paramB, int pageInit, int page) throws ServiceException;
	
	void addUnsincronized(Operation operation);
	
	
	
	
    
}
