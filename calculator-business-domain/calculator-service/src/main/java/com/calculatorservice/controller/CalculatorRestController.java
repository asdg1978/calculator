package com.calculatorservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public  @ResponseBody List<OperationDto> calculateWhitPercentage(@RequestParam  String paramA,@RequestParam  String paramB) {
        
    	//PONER VALIDACIONES, CONTROL DE EXCEPCIONES.. ETC
    	
    	List<Operation> operations =  calculatorService.calculateAddingPercentage(new Double(paramA), new Double(paramB));
    	return toDto(operations);    
    } 
    
    private List<OperationDto> toDto(List<Operation> operations){
    	List<OperationDto> result = new ArrayList<OperationDto>();
    	operations.stream().forEach(op->{
    		OperationDto dto = new OperationDto();
    		dto.setDescription(op.getDescription());
    		dto.setUrl(op.getUrl());
    		result.add(dto);
    	});
    	
    	return result;
    	
    }
    
    
    
    
}
