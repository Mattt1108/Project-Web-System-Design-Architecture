/**
 * Questa interfaccia rappresenta il repository per l'entità Cuoco.
 * Estende l'interfaccia JpaRepository per ottenere funzionalità di base per l'accesso ai dati.
 */
package com.panineria.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panineria.demo.model.Cuoco;

@Repository
public interface CuocoRepository extends JpaRepository<Cuoco, Integer> {

}