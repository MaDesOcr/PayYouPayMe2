package com.cda.PayYouPayMe.service;

import org.springframework.stereotype.Service;

import com.cda.PayYouPayMe.repository.MessageRepository;

@Service
public class MessageService {

	private final MessageRepository messageRepository;
	
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
}
