/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calculatorservice.remote;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.calculator.exceptions.RemoteException;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Component
public class ProviderConnector {
	
	@Autowired
    private WebClient.Builder webClientBuilder;

	
	TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.doOnConnected(connection -> {
				connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
			});

	public Double getPercentageIndex() throws RemoteException{
		Double result = null;
		try {
			WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
					.baseUrl("http://calculator-provider/provider/percentageIndex")				
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
					.defaultUriVariables(Collections.singletonMap("url", "http://calculator-provider/provider/percentageIndex"))
					.build();
			String block = client.method(HttpMethod.GET).uri("/").retrieve().bodyToMono(String.class).block();
			result = new Double(block);
		
		}catch(Exception ex) {			
			throw new RemoteException("Cant connect whit calculator-provider service", HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return result;

	}

}
