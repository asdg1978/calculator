package com.calculatorservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.exceptions.ApiError;
import com.calculator.exceptions.ControllerException;
import com.calculator.exceptions.ServiceException;
import com.calculatorservice.dto.OperationDto;
import com.calculatorservice.entities.Operation;
import com.calculatorservice.services.CalculatorService;



/**
 *
 * @author adelgrosso
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorRestController {
    
    @Autowired
    CalculatorService calculatorService;
    
   
    
    @GetMapping("/calculateWhitPercentage")
    public  @ResponseBody ResponseEntity<Object> calculateWhitPercentage(@RequestParam  String paramA,@RequestParam  String paramB) throws ControllerException{
        List<Operation> operations = null;
		try {
			operations = calculatorService.calculateAddingPercentage(new Double(paramA), new Double(paramB));
		} 
		 catch (ServiceException e) {
		 ApiError apiError = new ApiError(e.getHttpStatus(), e.getLocalizedMessage(), e.getMessage());
		 return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		}
    	return ResponseEntity.ok(toDto(operations));    
    } 
    
    private List<OperationDto> toDto(List<Operation> operations){
    	List<OperationDto> result = new ArrayList<OperationDto>();
    	operations.stream().forEach(op->{
    		OperationDto dto = new OperationDto();
    		dto.setOperation(op.getOperation());
    		dto.setId(op.getId());
    		result.add(dto);
    	});
    	
    	return result;
    	
    }
    
    
    
    
}
