package com.panineria.demo.model;

import jakarta.persistence.Entity;

/**
 * Questa classe rappresenta un cuoco all'interno del sistema.
 * Estende la classe Utente e implementa le funzionalità specifiche di un cuoco.
 */
@Entity
public class Cuoco extends Utente {

	/**
	 * Costruttore di default per la classe Cuoco.
	 * Inizializza il ruolo del cuoco come "CUOCO".
	 */
	public Cuoco() {
		super("CUOCO");
	}
}