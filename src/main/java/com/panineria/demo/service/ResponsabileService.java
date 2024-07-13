package com.panineria.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.Responsabile;
import com.panineria.demo.repository.ResponsabileRepository;

/**
 * Questa classe rappresenta il servizio per la gestione dei responsabili.
 */
@Service
public class ResponsabileService {

    @Autowired
    private ResponsabileRepository responsabileRepository;

    /**
     * Restituisce tutti i responsabili presenti nel repository.
     * 
     * @return un'iterabile contenente tutti i responsabili
     */
    public Iterable<Responsabile> getAllResponsabili() {
        return responsabileRepository.findAll();
    }
    
    /**
     * Restituisce il responsabile corrispondente all'id specificato.
     * 
     * @param id l'id del responsabile da cercare
     * @return un Optional contenente il responsabile, se presente
     */
    public Optional<Responsabile> getResponsabili(Integer id) {
        return responsabileRepository.findById(id);
    }
}
