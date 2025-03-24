package com.cda.PayYouPayMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cda.PayYouPayMe.model.Utilisateur;
import com.cda.PayYouPayMe.service.UtilisateurService;


@Controller
@RequestMapping("/me/user")
public class UtilisateurController {

	private final UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping("/")
	public String getCurrentUser(Model model) {
		Utilisateur utilisateur = utilisateurService.getCurrentUser();
		model.addAttribute("userToDisplay", utilisateur);
		return "affichageUser";
	}
	
	@PostMapping("/saveutilisateur")
	public String saveUtilisateur(Model model,
			@ModelAttribute Utilisateur userToSave) {
		
		
		utilisateurService.updateUser(userToSave);
		
		Utilisateur utilisateur = utilisateurService.getCurrentUser();
		model.addAttribute("userToDisplay", utilisateur);
		return "affichageUser";
	}
	
	@PostMapping("/suspendrecompte")
	public String supendreUtilisateur(Model model) {
		utilisateurService.suspendreCompte();
		return "home";
	}
}


