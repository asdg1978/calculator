package com.calculatorprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderRestController {
	
	
	@GetMapping("/percentageIndex")
    public String percentageIndex() {
        
    	return "10";
    	    
    } 

}
