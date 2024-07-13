package com.panineria.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.Corriere;
import com.panineria.demo.model.Ordine;
import com.panineria.demo.repository.CorriereRepository;

/**
 * Questa classe rappresenta il servizio per la gestione dei corrieri.
 */
@Service
public class CorriereService 
{
	@Autowired
	private CorriereRepository corriereRepository;
	@Autowired
	private OrdineService ordineService;
	
	/**
	 * Restituisce un corriere dato il suo username.
	 * 
	 * @param username l'username del corriere
	 * @return un Optional contenente il corriere se trovato, altrimenti vuoto
	 */
	public Optional<Corriere> getUtenteByUsername(String username) {
        return corriereRepository.findByUsername(username);
    } 
	
	/**
	 * Calcola il tempo medio di consegna per ogni corriere.
	 * 
	 * @return una mappa contenente l'ID del corriere come chiave e il tempo medio di consegna come valore
	 */
	public Map<Integer,Double> tempoMedioConsegnaCorriere()
    {
    	Map<Integer, Double> medie = new HashMap<>();
        Iterable<Corriere> corriere = this.getAllCorrieri();
        List<Ordine> ordiniCorrieri = ordineService.findByIdCorriere(corriere);

        Map<Integer, Long> sommaDifferenze = new HashMap<>();
        Map<Integer, Integer> conteggioOrdini = new HashMap<>();

        for (Ordine ordine : ordiniCorrieri) {
            int idCorriere = ordine.getIdCuoco();
            LocalDateTime timestampInLavorazione = ordine.getTimestamp_IN_LAVORAZIONE();
            LocalDateTime timestampConsegna = ordine.getTimestamp_CONSEGNA();
            long differenzaInSecondi;
            if (timestampConsegna != null && timestampInLavorazione != null) {
            	differenzaInSecondi = Duration.between(timestampInLavorazione, timestampConsegna).toSeconds();
                sommaDifferenze.put(idCorriere, sommaDifferenze.getOrDefault(idCorriere, 0L) + differenzaInSecondi);
                conteggioOrdini.put(idCorriere, conteggioOrdini.getOrDefault(idCorriere, 0) + 1);
            }
        }

        for (Integer idCorriere : sommaDifferenze.keySet()) {
            long somma = sommaDifferenze.get(idCorriere);
            int conteggio = conteggioOrdini.get(idCorriere);
            double media = (double) somma / conteggio;
            medie.put(idCorriere, media);
        }

        return medie;
    }

	private Iterable<Corriere> getAllCorrieri() {
		return corriereRepository.findAll();
	}
}
