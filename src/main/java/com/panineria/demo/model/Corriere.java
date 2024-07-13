package com.panineria.demo.model;

import jakarta.persistence.Entity;

/**
 * Questa classe rappresenta un corriere nel sistema.
 * Estende la classe Utente e viene utilizzata per gestire le informazioni dei corrieri.
 */
@Entity
public class Corriere extends Utente
{
	/**
	 * Costruttore di default per la classe Corriere.
	 * Inizializza il ruolo del corriere come "CORRIERE".
	 */
	public Corriere()
	{
		super("CORRIERE");
	}
}
