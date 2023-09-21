/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calculator.setups;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class GlobalPreFiltering implements GlobalFilter{
    
	int intents;
	
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        return chain.filter(exchange);
    }
    
    
    
    	
    
}


