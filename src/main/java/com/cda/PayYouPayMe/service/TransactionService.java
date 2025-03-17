package com.cda.PayYouPayMe.service;

import org.springframework.stereotype.Service;

import com.cda.PayYouPayMe.repository.TransactionRepository;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;

	}
}