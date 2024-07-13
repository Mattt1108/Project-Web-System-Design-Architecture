package com.panineria.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.panineria.demo.model.Panino;

/**
 * Questa interfaccia rappresenta il repository per l'entità Panino.
 * Estende l'interfaccia CrudRepository fornita da Spring Data.
 */
public interface PaninoRepository extends CrudRepository<Panino,Integer>{

	/**
	 * Trova un panino dato il suo nome.
	 * 
	 * @param nome il nome del panino da cercare
	 * @return un Optional contenente il panino trovato, se presente
	 */
	Optional<Panino> findByNome(String nome);

}
