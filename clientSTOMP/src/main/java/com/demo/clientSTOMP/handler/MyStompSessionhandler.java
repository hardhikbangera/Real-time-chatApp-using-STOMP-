package com.demo.clientSTOMP.handler;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionhandler extends StompSessionHandlerAdapter{

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.out.println("Connected to STOMP");
	}
	
	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		System.out.println(exception.getMessage());
	}
}
