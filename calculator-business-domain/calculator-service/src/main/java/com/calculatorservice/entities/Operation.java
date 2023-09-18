package com.calculatorservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 *
 * @author adelgrosso
 */


@Entity
@Data
public class Operation {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
   private long id;
   private String url;
   private String description;
      
   
}
