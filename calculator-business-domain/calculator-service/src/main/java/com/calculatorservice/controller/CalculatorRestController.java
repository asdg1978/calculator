package com.calculatorservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.exceptions.ApiError;
import com.calculator.exceptions.ControllerException;
import com.calculator.exceptions.ServiceException;
import com.calculator.utils.DateUtils;
import com.calculatorservice.dto.OperationDto;
import com.calculatorservice.dto.OperationRequestDto;
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
    
    
    @RequestMapping(method = RequestMethod.POST)
    public  @ResponseBody ResponseEntity<Object> create(
    			@RequestParam(value = "page") final Integer page,
    			@RequestParam(value = "size") final Integer size,            
            @RequestBody OperationRequestDto operationRequestDto) throws ControllerException {
    	if(page<1 || size<1) {
    		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "page and size must be greeter than 0","");
    		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    		
    	}
    	List<Operation> operations = null;
		try {
			operations = calculatorService.calculateAddingPercentage(operationRequestDto.getParamA(), operationRequestDto.getParamB(),Integer.valueOf(page),Integer.valueOf(size));
			} 
		 catch (ServiceException e) {
		 ApiError apiError = new ApiError(e.getHttpStatus(), e.getMessage(),"");
		 return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		}
    	return ResponseEntity.ok(toDto(operations));
    }
    
    
   
    private List<OperationDto> toDto(List<Operation> operations){
    	List<OperationDto> result = new ArrayList<OperationDto>();
    	operations.stream().forEach(op->{
    		OperationDto dto = new OperationDto();
    		dto.setOperation(op.getOperation()+" AT " +DateUtils.dateToString(op.getDate()));    		
    		result.add(dto);
    	});
    	
    	return result;
    	
    }
    
    
    
    
}
