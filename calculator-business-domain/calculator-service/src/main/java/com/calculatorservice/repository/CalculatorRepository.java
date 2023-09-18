/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calculatorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calculatorservice.entities.Operation;


/**
 *
 * @author adelgrosso
 */
public interface CalculatorRepository extends JpaRepository<Operation, Long> {


     
}
