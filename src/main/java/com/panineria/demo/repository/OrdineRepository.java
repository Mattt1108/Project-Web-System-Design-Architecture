package com.panineria.demo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.panineria.demo.model.Ordine;

/**
 * Questa interfaccia rappresenta il repository per l'entità Ordine.
 * Fornisce i metodi per eseguire operazioni di persistenza e recupero dei dati relativi agli ordini.
 */
@Repository
public interface OrdineRepository extends CrudRepository<Ordine,Integer>
{
	/**
	 * Restituisce un elenco di ordini in base allo stato specificato.
	 *
	 * @param stato lo stato degli ordini da cercare
	 * @return un elenco di ordini con lo stato specificato
	 */
	Iterable<Ordine> findByStato(String stato);

	/**
	 * Restituisce un elenco di ordini in base all'ID del cuoco specificato.
	 *
	 * @param idCuoco l'ID del cuoco associato agli ordini da cercare
	 * @return un elenco di ordini associati all'ID del cuoco specificato
	 */
	List<Ordine> findByIdCuoco(Integer idCuoco);

	/**
	 * Restituisce un elenco di ordini in base all'ID dell'addetto alla consegna specificato.
	 *
	 * @param idAddettoConsegna l'ID dell'addetto alla consegna associato agli ordini da cercare
	 * @return un elenco di ordini associati all'ID dell'addetto alla consegna specificato
	 */
	List<Ordine> findByIdAddettoConsegna(Integer idAddettoConsegna);
}
