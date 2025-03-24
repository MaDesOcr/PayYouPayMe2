package com.cda.PayYouPayMe.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cda.PayYouPayMe.model.Transaction;
import com.cda.PayYouPayMe.model.Utilisateur;
import com.cda.PayYouPayMe.repository.TransactionRepository;
import com.cda.PayYouPayMe.repository.UtilisateurRepository;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
    private final AuthentificationService authService;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;
    
	public TransactionService(TransactionRepository transactionRepository,
			AuthentificationService authService,
			UtilisateurRepository utilisateurRepository,
			UtilisateurService utilisateurService) {
		this.transactionRepository = transactionRepository;
		this.authService = authService;
		this.utilisateurRepository = utilisateurRepository;
		this.utilisateurService = utilisateurService;
	}

	public List<Transaction> getAllTransactions() {
		
		return this.transactionRepository.findAll();
	}
	    
    public List<Transaction> getCurrentUserTransactions() {
        String username = authService.getCurrentUsername();
        if (username != null) {
            return transactionRepository.findAllByUserLogin(username);
        }
        return Collections.emptyList();
    }
    
    public List<Transaction> getUserTransactions(Integer userId) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(userId);
        return utilisateur.map(u -> transactionRepository.findAllByUser(u))
                         .orElse(Collections.emptyList());
    }

	public void createTransaction(String username, Float amount, String content) {
		Utilisateur sender = utilisateurService.getCurrentUser();
		Utilisateur reciever = utilisateurService.getUserByUserName(username);
		
		if(sender.getBalance() > amount && amount > 0 && reciever!=null) {
			Transaction transactionToCreate = new Transaction();
			transactionToCreate.setAmount(amount);
			transactionToCreate.setDate(LocalDate.now());
			transactionToCreate.setMessageContent(content);
			transactionToCreate.setReciever(reciever);
			transactionToCreate.setSender(sender);
			
			transactionRepository.save(transactionToCreate);
			reciever.setBalance(reciever.getBalance()+amount);
			sender.setBalance(sender.getBalance()-amount);
			utilisateurService.updateUser(reciever);
			utilisateurService.updateUser(sender);
		}
		else {
			
		}
		
	}
}