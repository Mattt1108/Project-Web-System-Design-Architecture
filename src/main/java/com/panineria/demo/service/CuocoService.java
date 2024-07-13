package com.panineria.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.Cuoco;
import com.panineria.demo.model.Ordine;
import com.panineria.demo.repository.CuocoRepository;

/**
 * Questa classe rappresenta il servizio per la gestione dei cuochi.
 */
@Service
public class CuocoService {
    
    @Autowired
    private CuocoRepository cuocoRepository;
    
    @Autowired
    private OrdineService ordineService;

    /**
     * Restituisce tutti i cuochi presenti nel sistema.
     * 
     * @return l'elenco di tutti i cuochi
     */
    public Iterable<Cuoco> getAllCuochi() {
        return cuocoRepository.findAll();
    }

    /**
     * Restituisce il cuoco corrispondente all'ID specificato.
     * 
     * @param id l'ID del cuoco
     * @return il cuoco corrispondente all'ID specificato, se presente
     */
    public Optional<Cuoco> getCuocoById(Integer id) {
        return cuocoRepository.findById(id);
    }
    
    /**
     * Crea un nuovo cuoco nel sistema.
     * 
     * @param cuoco il cuoco da creare
     * @return il cuoco creato
     */
    public Cuoco create(Cuoco cuoco) { 
        return cuocoRepository.save(cuoco);
    }
    
    /**
     * Calcola il tempo medio di preparazione per ogni cuoco.
     * 
     * @return una mappa che associa l'ID del cuoco al tempo medio di preparazione
     */
    public Map<Integer, Double> tempoMedioPreparazioneCuoco() {
        Map<Integer, Double> medie = new HashMap<>();
        Iterable<Cuoco> cuochi = this.getAllCuochi();
        List<Ordine> ordiniCuochi = ordineService.findByIdCuochi(cuochi);

        Map<Integer, Long> sommaDifferenze = new HashMap<>();
        Map<Integer, Integer> conteggioOrdini = new HashMap<>();

        for (Ordine ordine : ordiniCuochi) {
            int idCuoco = ordine.getIdCuoco();
            LocalDateTime timestampAlBanco = ordine.getTimestamp_AL_BANCO();
            LocalDateTime timestampInLavorazione = ordine.getTimestamp_IN_LAVORAZIONE();
            LocalDateTime timestampConsegna = ordine.getTimestamp_CONSEGNA();
            long differenzaInSecondi;
            if (timestampAlBanco != null && timestampInLavorazione != null) {
                differenzaInSecondi = Duration.between(timestampInLavorazione, timestampAlBanco).toSeconds();
                sommaDifferenze.put(idCuoco, sommaDifferenze.getOrDefault(idCuoco, 0L) + differenzaInSecondi);
                conteggioOrdini.put(idCuoco, conteggioOrdini.getOrDefault(idCuoco, 0) + 1);
            } else if (timestampConsegna != null && timestampInLavorazione != null) {
                differenzaInSecondi = Duration.between(timestampInLavorazione, timestampConsegna).toSeconds();
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
