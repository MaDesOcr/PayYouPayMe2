package com.cda.PayYouPayMe.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//@Column(name="Prenom")
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String iban;
	
	private Float balance;
	
	private String login;
	private String password;
	
	@OneToMany(mappedBy = "sender")
	private List<Transaction> transactionSent;

	@OneToMany(mappedBy = "reciever")
	private List<Transaction> transactionRecieved;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Message> messageSent;
	
	@ManyToMany//(mappedBy = "contact")
	private List<Utilisateur> contact;
	
}
