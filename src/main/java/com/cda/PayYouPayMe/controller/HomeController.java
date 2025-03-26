package com.cda.PayYouPayMe.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cda.PayYouPayMe.model.Message;
import com.cda.PayYouPayMe.model.Utilisateur;
import com.cda.PayYouPayMe.service.UtilisateurService;


@Controller
public class HomeController {

    private final UtilisateurService utilisateurService;
    
    public HomeController(UtilisateurService utilisateurService) {
    	this.utilisateurService = utilisateurService;
    }

	

	@GetMapping("/")
	public String home(Model model) {
		if(utilisateurService.getCurrentUser()!=null) model.addAttribute(utilisateurService.getCurrentUser());
		return "home";
	}
	
	@PostMapping("/signUp")
	public String signUp(Model model, @RequestParam String userName,
			@RequestParam String password) {
		try {
			utilisateurService.createUser(userName, password);
		}
		catch (Exception e) {
			model.addAttribute("Erreur", "UserName déjà utilisé");
		}
		return "home";
	}
}
