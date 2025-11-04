package com.demo.clientSTOMP;
import com.demo.clientSTOMP.Model.Message;
import com.demo.clientSTOMP.handler.MyStompSessionhandler;

import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class App{
	static Scanner scanner=new Scanner(System.in);
	 private static final String URL = "ws://localhost:8080/chat";
    public static void main( String[] args ){
        WebSocketStompClient stompClient=new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        
        
        CompletableFuture<StompSession> future=stompClient.connectAsync(URL,
        																   new WebSocketHttpHeaders(), 
        																   new MyStompSessionhandler());
        StompSession session=null;
        try {
			session= future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
        System.out.println("Enter your username");
        String username=scanner.nextLine();
        
        
        session.subscribe("/topic/message", new StompFrameHandler() {

			@Override
			public Type getPayloadType(StompHeaders headers) {
				return Message.class;
			}

			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				Message message=(Message)payload;
				if(!message.getUser().equalsIgnoreCase(username)) {
				System.out.println(message.getUser()+" : "+message.getMessage());
				}
			}
        	
        });
        System.out.println("Enter your message");
        
        while(true) {
            String result=scanner.nextLine();
        	if(result.equals("exit")) {break;}
        	session.send("/topic/message", new Message(username,result));
        }
        session.disconnect();
        stompClient.stop();
    
    }
}
