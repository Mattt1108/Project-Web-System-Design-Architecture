
package com.panineria.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.panineria.demo.model.Ordine;
import com.panineria.demo.service.OrdineService;
import com.panineria.demo.service.UtenteService;

/**
 * Questa classe rappresenta il controller per le operazioni relative all'addetto alla consegna degli ordini.
 */
@Controller
@RequestMapping("/addettoConsegna")
public class AddettoConsegnaController 
{
	
	@Autowired
	private OrdineService ordineService;
	@Autowired
	private UtenteService utenteService;
	
	/**
	 * Metodo che gestisce la richiesta GET per la dashboard dell'addetto alla consegna.
	 * 
	 * @param model il modello utilizzato per passare dati alla vista
	 * @return il nome della vista da visualizzare
	 */
	@GetMapping("/dashboard")
	public String dashboard(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Integer idAddettoConsegna=utenteService.getUtenteByUsername(userDetails.getUsername()).get().getId();

		Iterable<Ordine> ordiniAlBanco = ordineService.findByStato("AL_BANCO");
		model.addAttribute("idAddettoConsegna",idAddettoConsegna);
		model.addAttribute("ordini_AL_BANCO", ordiniAlBanco);
		return "addettoConsegnaDashboard";
	}

	/**
	 * Metodo che gestisce la richiesta POST per completare un ordine.
	 * 
	 * @param ordineId l'ID dell'ordine da completare
	 * @param idAddettoConsegna l'ID dell'addetto alla consegna
	 * @return il nome della vista da visualizzare
	 */
	@PostMapping("/completa-ordine") 
	public String completaOrdine(@RequestParam("ordineId") Integer ordineId, @RequestParam("idAddettoConsegna") Integer idAddettoConsegna) {
		ordineService.setCompletato(ordineId,idAddettoConsegna);
		
		return "redirect:/addettoConsegna/dashboard";
	}
	
}

