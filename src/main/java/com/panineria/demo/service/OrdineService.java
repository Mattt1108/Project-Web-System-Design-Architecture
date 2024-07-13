package com.panineria.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.AddettoConsegna;
import com.panineria.demo.model.Corriere;
import com.panineria.demo.model.Cuoco;
import com.panineria.demo.model.Ordine;
import com.panineria.demo.repository.OrdineRepository;

/**
 * Questa classe rappresenta il servizio per la gestione degli ordini.
 */
@Service
public class OrdineService 
{
	@Autowired
	private OrdineRepository ordineRepository;
	
	/**
	 * Crea un nuovo ordine.
	 * 
	 * @param ordine l'ordine da creare
	 * @return l'ordine creato
	 */
	public Ordine create(Ordine ordine)
	{ 
		return ordineRepository.save(ordine);
	}

	/**
	 * Trova gli ordini con lo stato specificato.
	 * 
	 * @param stato lo stato degli ordini da trovare
	 * @return gli ordini con lo stato specificato
	 */
	public Iterable<Ordine> findByStato(String stato) {
		
		return ordineRepository.findByStato(stato);
		
	}

	/**
	 * Imposta lo stato dell'ordine come "IN_LAVORAZIONE" e l'id del cuoco che lo sta preparando.
	 * 
	 * @param ordineId l'id dell'ordine
	 * @param idCuoco l'id del cuoco
	 */
	public void setInLavorazione(Integer ordineId,Integer idCuoco) {
		
		Optional<Ordine>ordine= ordineRepository.findById(ordineId);
		if(!ordine.isEmpty())
		{
			ordine.get().setStato("IN_LAVORAZIONE");
			ordine.get().setIdCuoco(idCuoco);
			ordine.get().setTimestamp_IN_LAVORAZIONE(LocalDateTime.now());
			ordineRepository.save(ordine.get());
		}
	}
		
	/**
	 * Imposta lo stato dell'ordine come "AL_BANCO".
	 * 
	 * @param ordineId l'id dell'ordine
	 */
	public void setAlBanco(Integer ordineId) {
		
		Optional<Ordine>ordine= ordineRepository.findById(ordineId);
		
		if(!ordine.isEmpty())
		{
			ordine.get().setStato("AL_BANCO");
			ordine.get().setTimestamp_AL_BANCO(LocalDateTime.now());
			ordineRepository.save(ordine.get());
		}
		
	}

	/**
	 * Imposta lo stato dell'ordine come "COMPLETATO" e l'id dell'addetto alla consegna.
	 * 
	 * @param ordineId l'id dell'ordine
	 * @param idAddettoConsegna l'id dell'addetto alla consegna
	 */
	public void setCompletato(Integer ordineId, Integer idAddettoConsegna) {

		Optional<Ordine>ordine= ordineRepository.findById(ordineId);
		
		if(!ordine.isEmpty())
		{
			ordine.get().setStato("COMPLETATO");
			ordine.get().setTimestamp_COMPLETATO(LocalDateTime.now());
			ordine.get().setIdAddettoConsegna(idAddettoConsegna);
			ordineRepository.save(ordine.get());
		}
		
	}
	
	
	/**
	 * Trova gli ordini associati ai cuochi specificati.
	 * 
	 * @param cuochi gli oggetti Cuoco
	 * @return una lista di ordini associati ai cuochi specificati
	 */
	public List<Ordine> findByIdCuochi(Iterable<Cuoco> cuochi) {
        List<Ordine> ordini = new ArrayList<>();
        for (Cuoco cuoco : cuochi) {
            ordini.addAll(ordineRepository.findByIdCuoco(cuoco.getId()));
        }
        return ordini;
    }

	
	/**
	 * Calcola il tempo medio di preparazione degli ordini.
	 * 
	 * @return il tempo medio di preparazione degli ordini
	 */
	public Double getTMedioPreparazione()
	{
		short nTempi=0;
		long sommaDifferenzeTempi=0;
		Iterable<Ordine> ordini = ordineRepository.findAll();
		
		for(Ordine ordine : ordini)
		{
			LocalDateTime timestampConsegna = ordine.getTimestamp_CONSEGNA();
			LocalDateTime timestampAlBanco = ordine.getTimestamp_AL_BANCO();
	        LocalDateTime timestampInLavorazione = ordine.getTimestamp_IN_LAVORAZIONE();
	        if (timestampAlBanco != null && timestampInLavorazione != null) {
	        	nTempi++;
	            sommaDifferenzeTempi += Duration.between(timestampInLavorazione, timestampAlBanco).toSeconds();
	        }else if (timestampConsegna != null && timestampInLavorazione != null) {
	        	nTempi++;
	            sommaDifferenzeTempi += Duration.between(timestampInLavorazione, timestampConsegna).toSeconds();
	        }
		}
		
		Double Tmedio = (nTempi == 0) ? 0.0 : (sommaDifferenzeTempi / nTempi);

		
		return Tmedio;
		
	}
	
	/**
	 * Calcola il tempo medio di consegna degli ordini al banco.
	 * 
	 * @return il tempo medio di consegna degli ordini al banco
	 */
	public Double getTMedioConsegnaBanco() 
	{
		short nTempi=0;
		long sommaDifferenzeTempi=0;
		Iterable<Ordine> ordini = ordineRepository.findAll();
		
		for(Ordine ordine : ordini)
		{
			LocalDateTime timestampCompletato = ordine.getTimestamp_COMPLETATO();
			LocalDateTime timestampAlBanco = ordine.getTimestamp_AL_BANCO();
	        
	        if (timestampAlBanco != null && timestampCompletato != null) {
	        	nTempi++;
	            sommaDifferenzeTempi += Duration.between(timestampAlBanco,timestampCompletato).toSeconds();
	        }
		}
		
		Double Tmedio = (nTempi == 0) ? 0.0 : (sommaDifferenzeTempi / nTempi);

		
		return Tmedio;
		
	}
	
	/**
	 * Calcola il tempo medio di consegna degli ordini da parte del corriere.
	 * 
	 * @return il tempo medio di consegna degli ordini da parte del corriere
	 */
	public Double getTMedioConsegnaCorriere() 
	{
		short nTempi=0;
		long sommaDifferenzeTempi=0;
		Iterable<Ordine> ordini = ordineRepository.findAll();
		
		for(Ordine ordine : ordini)
		{
			LocalDateTime timestampCompletato = ordine.getTimestamp_COMPLETATO();
			LocalDateTime timestampConsegna = ordine.getTimestamp_CONSEGNA();
	        
	        if (timestampConsegna != null && timestampCompletato != null) {
	        	nTempi++;
	            sommaDifferenzeTempi += Duration.between(timestampConsegna,timestampCompletato).toSeconds();
	        }
		}
		
		Double Tmedio = (nTempi == 0) ? 0.0 : (sommaDifferenzeTempi / nTempi);

		
		return Tmedio;
		
	}
	
	/**
	 * Calcola il tempo medio di attesa degli ordini.
	 * 
	 * @return il tempo medio di attesa degli ordini
	 */
	public Double getTMedioAttesa() 
	{
		short nTempi=0;
		long sommaDifferenzeTempi=0;
		Iterable<Ordine> ordini = ordineRepository.findAll();
		
		for(Ordine ordine : ordini)
		{
			LocalDateTime timestampCompletato = ordine.getTimestamp_COMPLETATO();
			LocalDateTime timestampDaLavorare = ordine.getTimestamp_DA_LAVORARE();
	        
	        if (timestampDaLavorare != null && timestampCompletato != null) {
	        	nTempi++;
	            sommaDifferenzeTempi += Duration.between(timestampDaLavorare, timestampCompletato).toSeconds();
	        }
		}
		
		Double Tmedio = (nTempi == 0) ? 0.0 : (sommaDifferenzeTempi / nTempi);

		
		return Tmedio;
		
	}
	
	/**
	 * Trova gli ordini associati agli addetti alla consegna specificati.
	 * 
	 * @param addettiConsegna gli oggetti AddettoConsegna
	 * @return una lista di ordini associati agli addetti alla consegna specificati
	 */
	public List<Ordine> findByIdAddettoConsegna(Iterable<AddettoConsegna> addettiConsegna) {
        List<Ordine> ordini = new ArrayList<>();
        for (AddettoConsegna addettoConsegna : addettiConsegna) {
            ordini.addAll(ordineRepository.findByIdAddettoConsegna(addettoConsegna.getId()));
        }
        return ordini;
    }
	
	/**
	 * Trova gli ordini associati ai corrieri specificati.
	 * 
	 * @param corrieri gli oggetti Corriere
	 * @return una lista di ordini associati ai corrieri specificati
	 */
	public List<Ordine> findByIdCorriere(Iterable<Corriere> corrieri) {
        List<Ordine> ordini = new ArrayList<>();
        for (Corriere corriere : corrieri) {
            ordini.addAll(ordineRepository.findByIdAddettoConsegna(corriere.getId()));
        }
        return ordini;
    }

	/**
	 * Trova l'ordine con l'id specificato.
	 * 
	 * @param ordineId l'id dell'ordine
	 * @return l'ordine con l'id specificato
	 */
	public Optional<Ordine> findById(Integer ordineId) {
		
		return ordineRepository.findById(ordineId);
	}

	/**
	 * Imposta lo stato dell'ordine come "CONSEGNA".
	 * 
	 * @param ordineId l'id dell'ordine
	 */
	public void setConsegnare(Integer ordineId) {

		Optional<Ordine>ordine= ordineRepository.findById(ordineId);
		if(!ordine.isEmpty())
		{
			ordine.get().setStato("CONSEGNA");
			ordine.get().setTimestamp_CONSEGNA(LocalDateTime.now());
			ordineRepository.save(ordine.get());
		}
		
	}
}
