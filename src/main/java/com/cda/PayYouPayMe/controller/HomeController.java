package com.cda.PayYouPayMe.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cda.PayYouPayMe.model.Message;
import com.cda.PayYouPayMe.model.Utilisateur;
import com.cda.PayYouPayMe.service.UtilisateurService;

import ch.qos.logback.core.model.Model;

@Controller
public class HomeController {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurService utilisateurService;
    
    public HomeController(PasswordEncoder passwordEncoder,
    		UtilisateurService utilisateurService) {
    	this.passwordEncoder = passwordEncoder;
    	this.utilisateurService = utilisateurService;
    }

	
	//@GetMapping("/home")
	@GetMapping("/")
	public String home() {
		//vide?
		return "home";
	}
	
	@PostMapping("/signUp")
	public String signUp(Model model, @RequestParam String userName,
			@RequestParam String password) {
		//pas au bon endroit
		Utilisateur utilisateurToSave = new Utilisateur();
		utilisateurToSave.setUsername(userName);
		utilisateurToSave.setPassword(passwordEncoder.encode(password));
		utilisateurToSave.setActif(true);
		utilisateurToSave.setBalance(0f);
		utilisateurToSave.setRole("USER");
		utilisateurToSave.setConfirmer(false);
		utilisateurService.createUser(utilisateurToSave);
		
		return "home";
	}
}
