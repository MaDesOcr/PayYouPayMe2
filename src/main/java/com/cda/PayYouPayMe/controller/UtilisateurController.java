package com.cda.PayYouPayMe.controller;

import org.springframework.stereotype.Controller;

import com.cda.PayYouPayMe.service.UtilisateurService;

@Controller
public class UtilisateurController {

	private final UtilisateurService utilisateurService;
	
	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
}
