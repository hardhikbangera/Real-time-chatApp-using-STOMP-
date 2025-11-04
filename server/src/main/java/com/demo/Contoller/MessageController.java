package com.demo.Contoller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.demo.model.MessageModel;

@Controller
public class MessageController {

	@MessageMapping("/send")
	@SendTo("/topic/message")
	public MessageModel send(MessageModel model) {
		IO.println("Message Recieved : "+model.getMessage());
		return new MessageModel(model.getUser(),model.getMessage());
		
	}
}
