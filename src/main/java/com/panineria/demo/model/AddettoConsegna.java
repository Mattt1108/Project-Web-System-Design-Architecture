
package com.panineria.demo.model;

import jakarta.persistence.Entity;

/**
 * Questa classe rappresenta un addetto alla consegna all'interno di una panineria.
 * Estende la classe Utente e implementa le funzionalità specifiche per un addetto alla consegna.
 */
@Entity
public class AddettoConsegna extends Utente {
  
	/**
	 * Costruttore di default per la classe AddettoConsegna.
	 * Richiama il costruttore della classe padre Utente e imposta il ruolo come "ADDETTOCONSEGNA".
	 */
	public AddettoConsegna()
	{
		super("ADDETTOCONSEGNA");
	}
    
}

