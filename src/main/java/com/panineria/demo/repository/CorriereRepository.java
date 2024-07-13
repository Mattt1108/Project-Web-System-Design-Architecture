package com.panineria.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panineria.demo.model.Corriere;

/**
 * Questa interfaccia rappresenta il repository per l'entità Corriere.
 * Estende l'interfaccia CrudRepository per fornire operazioni di base di CRUD (Create, Read, Update, Delete).
 */
@Repository
public interface CorriereRepository extends CrudRepository<Corriere,Integer>{

	/**
	 * Trova un corriere dato il suo username.
	 * 
	 * @param username l'username del corriere da cercare
	 * @return un Optional contenente il corriere trovato, se presente
	 */
	Optional<Corriere> findByUsername(String username);
}
