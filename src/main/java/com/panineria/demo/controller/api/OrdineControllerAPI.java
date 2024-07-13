package com.panineria.demo.controller.api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panineria.demo.model.Ordine;
import com.panineria.demo.service.MenuService;
import com.panineria.demo.service.OrdineService;

/**
 * Questa classe rappresenta il controller API per la gestione degli ordini.
 */
@RestController
@RequestMapping("/api/ordine")
public class OrdineControllerAPI {

	@Autowired
	OrdineService ordineService;
	@Autowired
	MenuService menuService;

	/**
	 * Crea un nuovo ordine.
	 * 
	 * @param ordine l'oggetto Ordine da creare
	 * @param model  il modello per la vista
	 * @return l'ordine creato
	 */
	@PostMapping("/creaOrdine")
	public Ordine create(@RequestBody Ordine ordine, Model model) {
		Double costoTotale;
		costoTotale = menuService.getBibitaByNome(ordine.getBibita()).get().getPrezzo();
		costoTotale += menuService.getPaninoByNome(ordine.getPanino()).get().getPrezzo();
		costoTotale += menuService.getContornoByNome(ordine.getContorno()).get().getPrezzo();
		ordine.setCostoTotale(costoTotale);
		ordine.genCodiceRitiro();
		ordine.setStato("DA_LAVORARE");
		ordine.setTimestamp_DA_LAVORARE(LocalDateTime.now());
		ordineService.create(ordine);
		return ordine;
	}
}