package com.panineria.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.Utente;
import com.panineria.demo.repository.UtenteRepository;

/**
 * Questa classe rappresenta il servizio per la gestione degli utenti.
 */
@Service
public class UtenteService 
{
	@Autowired
	private UtenteRepository utenteRepository;
	
	/**
	 * Restituisce un utente dato il suo username.
	 * 
	 * @param username l'username dell'utente da cercare
	 * @return un Optional contenente l'utente trovato, se presente
	 */
	public Optional<Utente> getUtenteByUsername(String username) {
        return utenteRepository.findByUsername(username);
    } 
}
