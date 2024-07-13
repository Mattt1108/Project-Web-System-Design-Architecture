package com.panineria.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.panineria.demo.model.Bibita;

/**
 * Questa interfaccia rappresenta il repository per l'entità Bibita.
 * Estende l'interfaccia CrudRepository per fornire operazioni di base di CRUD (Create, Read, Update, Delete).
 */
public interface BibitaRepository extends CrudRepository<Bibita,Integer>{

	/**
	 * Trova una Bibita tramite il suo nome.
	 * 
	 * @param nome il nome della Bibita da cercare
	 * @return un Optional contenente la Bibita trovata, se presente
	 */
	Optional<Bibita> findByNome(String nome);

}
