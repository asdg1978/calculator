package com.calculatorservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class OperationDto {
	@JsonIgnore
	private long id;
	private String operation;	
}
