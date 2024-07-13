package com.panineria.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Questa classe rappresenta un utente nel sistema.
 */
@Entity
public class Utente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String nome;
	private String ruolo;
	
	/**
	 * Costruttore della classe Utente.
	 * 
	 * @param ruolo il ruolo dell'utente
	 */
	public Utente(String ruolo)
	{
		this.ruolo=ruolo;
	}
	
	// Getters and setters
	
	/**
	 * Restituisce l'ID dell'utente.
	 * 
	 * @return l'ID dell'utente
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Imposta l'ID dell'utente.
	 * 
	 * @param id l'ID dell'utente
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Restituisce lo username dell'utente.
	 * 
	 * @return lo username dell'utente
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Imposta lo username dell'utente.
	 * 
	 * @param username lo username dell'utente
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Restituisce la password dell'utente.
	 * 
	 * @return la password dell'utente
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Imposta la password dell'utente.
	 * 
	 * @param password la password dell'utente
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Restituisce il nome dell'utente.
	 * 
	 * @return il nome dell'utente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il nome dell'utente.
	 * 
	 * @param nome il nome dell'utente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Restituisce il ruolo dell'utente.
	 * 
	 * @return il ruolo dell'utente
	 */
	public String getRuolo() {
		return ruolo;
	}

	/**
	 * Imposta il ruolo dell'utente.
	 * 
	 * @param ruolo il ruolo dell'utente
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
}
