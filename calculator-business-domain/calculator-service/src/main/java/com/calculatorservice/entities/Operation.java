package com.calculatorservice.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
   private String operation;
   
   @Temporal(TemporalType.TIMESTAMP)
   private Date date;
      
   
}
