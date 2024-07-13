package com.panineria.demo.model;

import jakarta.persistence.Entity;

/**
 * Questa classe rappresenta un cliente registrato nel sistema.
 * Estende la classe Utente e aggiunge le informazioni specifiche del cliente registrato.
 */
@Entity
public class ClienteRegistrato extends Utente 
{
	/**
	 * Costruttore di default della classe ClienteRegistrato.
	 * Richiama il costruttore della classe padre Utente e imposta il ruolo come "CLIENTEREGISTRATO".
	 */
	public ClienteRegistrato()
	{
		super("CLIENTEREGISTRATO"); 
	}
	
	private String indirizzo;
	private String recapito; //numero di telefono
	
	/**
	 * Restituisce l'indirizzo del cliente registrato.
	 * @return l'indirizzo del cliente registrato
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * Imposta l'indirizzo del cliente registrato.
	 * @param indirizzo l'indirizzo da impostare
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Restituisce il recapito telefonico del cliente registrato.
	 * @return il recapito telefonico del cliente registrato
	 */
	public String getRecapito() {
		return recapito;
	}

	/**
	 * Imposta il recapito telefonico del cliente registrato.
	 * @param recapito il recapito telefonico da impostare
	 */
	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}
}
