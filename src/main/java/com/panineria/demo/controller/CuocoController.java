
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

import com.panineria.demo.service.OrdineService;
import com.panineria.demo.service.UtenteService;

/**
 * La classe CuocoController gestisce le richieste relative alla dashboard del cuoco.
 */
@Controller
@RequestMapping("/cuoco")
public class CuocoController 
{
	@Autowired
	private OrdineService ordineService;
	
	@Autowired
	private UtenteService utenteService;
	
	/**
	 * Gestisce la richiesta GET per la pagina della dashboard del cuoco.
	 * Recupera l'ID del cuoco, gli ordini da lavorare e gli ordini in corso.
	 * 
	 * @param model l'oggetto model da popolare con i dati per la vista
	 * @return il nome della vista da renderizzare
	 */
	@GetMapping("/dashboard")
	public String cuocoDashboard(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Integer idCuoco=utenteService.getUtenteByUsername(userDetails.getUsername()).get().getId();
		
		model.addAttribute("idCuoco",idCuoco);
		model.addAttribute("ordini_DA_LAVORARE",ordineService.findByStato("DA_LAVORARE"));
		model.addAttribute("ordini_IN_LAVORAZIONE",ordineService.findByStato("IN_LAVORAZIONE"));
		return "cuocoDashboard"; 
	}
	
	/**
	 * Gestisce la richiesta POST per iniziare a lavorare su un ordine.
	 * Imposta lo stato dell'ordine a "IN_LAVORAZIONE".
	 * 
	 * @param ordineId l'ID dell'ordine su cui lavorare
	 * @param idCuoco l'ID del cuoco che lavora sull'ordine
	 * @return l'URL di reindirizzamento alla dashboard del cuoco
	 */
	@PostMapping("/inizia-lavorazione")
	public String iniziaLavorazione(@RequestParam("ordineId") Integer ordineId, @RequestParam("idCuoco") Integer idCuoco) {
		ordineService.setInLavorazione(ordineId,idCuoco);
		return "redirect:/cuoco/dashboard";
	}
	
	/**
	 * Gestisce la richiesta POST per completare un ordine al banco o per la consegna.
	 * Imposta lo stato dell'ordine a "AL_BANCO" se è per il banco, o "CONSEGNARE" se è per la consegna.
	 * 
	 * @param ordineId l'ID dell'ordine da completare
	 * @return l'URL di reindirizzamento alla dashboard del cuoco
	 */
	@PostMapping("/completa-banco")
	public String completaOrdine(@RequestParam Integer ordineId) {
		String indirizzo= ordineService.findById(ordineId).get().getIndirizzo();
		
		if(indirizzo == null) {
			// caso al banco
			ordineService.setAlBanco(ordineId);
		} else {
			// caso consegna cliente registrato
			ordineService.setConsegnare(ordineId);
		}
		
		return "redirect:/cuoco/dashboard";
	}
}


