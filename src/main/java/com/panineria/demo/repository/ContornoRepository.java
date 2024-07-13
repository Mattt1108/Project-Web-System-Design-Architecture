package com.panineria.demo.repository;

/**
 * Questa classe rappresenta un repository per l'entità Contorno.
 * Fornisce metodi per accedere e manipolare i dati relativi ai contorni.
 */
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.panineria.demo.model.Contorno;

public interface ContornoRepository extends CrudRepository<Contorno,Integer> {

	Optional<Contorno> findByNome(String nome);

}
