package com.panineria.demo.model;

import jakarta.persistence.Entity;

/**
 * Questa classe rappresenta un Responsabile all'interno del sistema.
 * Estende la classe Utente e implementa le funzionalità specifiche di un Responsabile.
 */
@Entity
public class Responsabile extends Utente {
	  
	/**
	 * Costruttore di default per la classe Responsabile.
	 * Richiama il costruttore della classe padre Utente e imposta il ruolo come "RESPONSABILE".
	 */
	public Responsabile()
	{
		super("RESPONSABILE");
	}
    
}