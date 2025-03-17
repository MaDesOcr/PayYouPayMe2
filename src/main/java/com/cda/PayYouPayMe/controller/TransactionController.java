package com.cda.PayYouPayMe.controller;

import org.springframework.stereotype.Controller;

import com.cda.PayYouPayMe.service.TransactionService;

@Controller
public class TransactionController {

	private final TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
}

