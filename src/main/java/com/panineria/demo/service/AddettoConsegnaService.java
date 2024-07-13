package com.panineria.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.AddettoConsegna;

import com.panineria.demo.model.Ordine;
import com.panineria.demo.repository.AddettoConsegnaRepository;

/**
 * Questa classe rappresenta il servizio per la gestione degli addetti alla consegna degli ordini.
 */
@Service
public class AddettoConsegnaService {

    @Autowired
    private AddettoConsegnaRepository addettoConsegnaRepository;
    @Autowired
    private OrdineService ordineService;

    /**
     * Restituisce tutti gli addetti alla consegna presenti nel sistema.
     * 
     * @return un'iterabile contenente tutti gli addetti alla consegna
     */
    public Iterable<AddettoConsegna> getAllAddettiConsegna() {
        return addettoConsegnaRepository.findAll();
    }

    /**
     * Restituisce l'addetto alla consegna con l'ID specificato.
     * 
     * @param id l'ID dell'addetto alla consegna
     * @return l'addetto alla consegna con l'ID specificato, se presente
     */
    public Optional<AddettoConsegna> getAddettoConsegna(Integer id) {
        return addettoConsegnaRepository.findById(id);
    }
    
    /**
     * Calcola il tempo medio di consegna per ogni addetto alla consegna.
     * 
     * @return una mappa contenente l'ID dell'addetto alla consegna e il tempo medio di consegna associato
     */
    public Map<Integer,Double> tempoMedioConsegnaAddettoConsegna() {
    	
    	Map<Integer, Double> medie = new HashMap<>();
        Iterable<AddettoConsegna> addettiConsegna = addettoConsegnaRepository.findAll();
        List<Ordine> ordiniCuochi = ordineService.findByIdAddettoConsegna(addettiConsegna);

        Map<Integer, Long> sommaDifferenze = new HashMap<>();
        Map<Integer, Integer> conteggioOrdini = new HashMap<>();

        for (Ordine ordine : ordiniCuochi) {
            int idCuoco = ordine.getIdCuoco();
            LocalDateTime timestampAlBanco = ordine.getTimestamp_AL_BANCO();
            LocalDateTime timestampInLavorazione = ordine.getTimestamp_IN_LAVORAZIONE();
            if (timestampAlBanco != null && timestampInLavorazione != null) {
                long differenzaInSecondi = Duration.between(timestampInLavorazione, timestampAlBanco).toSeconds();
                sommaDifferenze.put(idCuoco, sommaDifferenze.getOrDefault(idCuoco, 0L) + differenzaInSecondi);
                conteggioOrdini.put(idCuoco, conteggioOrdini.getOrDefault(idCuoco, 0) + 1);
            }
        }

        for (Integer idCuoco : sommaDifferenze.keySet()) {
            long somma = sommaDifferenze.get(idCuoco);
            int conteggio = conteggioOrdini.get(idCuoco);
            double media = (double) somma / conteggio;
            medie.put(idCuoco, media);
        }

        return medie;
    }

}
