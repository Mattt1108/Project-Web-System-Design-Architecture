/**
 * Questa interfaccia rappresenta il repository per la gestione dei dati degli utenti registrati.
 * Estende l'interfaccia CrudRepository fornita da Spring Data per l'accesso ai dati.
 */
package com.panineria.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.panineria.demo.model.ClienteRegistrato;

public interface ClienteRegistratoRepository extends CrudRepository<ClienteRegistrato,Integer>
{
}
