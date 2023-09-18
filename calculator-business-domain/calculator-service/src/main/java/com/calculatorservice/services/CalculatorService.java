
package com.calculatorservice.services;

import java.util.List;

import com.calculatorservice.entities.Operation;

public interface CalculatorService {
    
	public List<Operation> calculateAddingPercentage(Double paramA,Double paramB);
	
	public void add(Operation operation);
	
	public List<Operation> getAll();
	
    
}
