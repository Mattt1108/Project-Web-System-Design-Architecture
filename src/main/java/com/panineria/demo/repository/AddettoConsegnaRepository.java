/**
 * Questa interfaccia rappresenta il repository per la gestione degli addetti alla consegna.
 * Estende l'interfaccia CrudRepository per ottenere le funzionalità di base di un repository.
 */
package com.panineria.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panineria.demo.model.AddettoConsegna;

@Repository
public interface AddettoConsegnaRepository extends CrudRepository<AddettoConsegna,Integer>
{
	}
