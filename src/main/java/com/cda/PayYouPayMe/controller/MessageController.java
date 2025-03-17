package com.cda.PayYouPayMe.controller;

import org.springframework.stereotype.Controller;

import com.cda.PayYouPayMe.service.MessageService;

@Controller
public class MessageController {

	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
}
