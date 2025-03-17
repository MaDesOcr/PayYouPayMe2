package com.cda.PayYouPayMe.service;

import org.springframework.stereotype.Service;

import com.cda.PayYouPayMe.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	private final UtilisateurRepository utilisateurRepository;
	
	public UtilisateurService(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}
}
