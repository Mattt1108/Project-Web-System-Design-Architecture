/**
 * Questa interfaccia rappresenta il repository per l'entità Responsabile.
 * Estende l'interfaccia CrudRepository fornita da Spring Data per ottenere
 * funzionalità di base per la gestione dei dati.
 */
package com.panineria.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panineria.demo.model.Responsabile;

@Repository
public interface ResponsabileRepository extends CrudRepository<Responsabile,Integer> {

}
