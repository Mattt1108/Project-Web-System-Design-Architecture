package com.panineria.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panineria.demo.model.Utente;

/**
 * Questa interfaccia rappresenta il repository per l'entità Utente.
 * Estende l'interfaccia CrudRepository per ottenere le funzionalità di base di un repository.
 */
@Repository
public interface UtenteRepository extends CrudRepository<Utente,Integer>
{
	/**
	 * Trova un utente dato il suo username.
	 * 
	 * @param username l'username dell'utente da cercare
	 * @return un Optional contenente l'utente trovato, se presente
	 */
	Optional<Utente> findByUsername(String username);
}
