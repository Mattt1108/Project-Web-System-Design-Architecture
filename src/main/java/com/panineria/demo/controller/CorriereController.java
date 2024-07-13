
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
import com.panineria.demo.service.CorriereService;
import com.panineria.demo.service.OrdineService;

/**
 * This class represents the controller for the Corriere entity.
 * It handles the requests related to the Corriere entity and interacts with the CorriereService and OrdineService.
 */
@Controller
@RequestMapping("/corriere")
public class CorriereController
{
	@Autowired
	private OrdineService ordineService;
	@Autowired
	private CorriereService corriereService;
	
	/**
	 * This method handles the GET request to "/corriere/dashboard" and displays the Corriere dashboard.
	 * It retrieves the authenticated user's details and the list of orders with status "CONSEGNA" from the database.
	 * The user's ID and the list of orders are added to the model and passed to the "CorriereDashboard" view.
	 * 
	 * @param model the model object to add attributes to
	 * @return the name of the view to render
	 */
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Integer idCorriere=corriereService.getUtenteByUsername(userDetails.getUsername()).get().getId();
		Iterable<Ordine> ordiniConsegna = ordineService.findByStato("CONSEGNA");
		
		model.addAttribute("idCorriere",idCorriere);
		model.addAttribute("ordini_CONSEGNA", ordiniConsegna);
		return "CorriereDashboard";
	}

	/**
	 * This method handles the POST request to "/corriere/completa-ordine" and completes an order.
	 * It takes the order ID and the courier ID as parameters and calls the ordineService to set the order as completed.
	 * After completing the order, it redirects the user to the Corriere dashboard.
	 * 
	 * @param ordineId the ID of the order to complete
	 * @param idCorriere the ID of the courier
	 * @return the redirect URL to the Corriere dashboard
	 */
	@PostMapping("/completa-ordine") 
	public String completaOrdine(@RequestParam("ordineId") Integer ordineId, @RequestParam("idCorriere") Integer idCorriere) {
		ordineService.setCompletato(ordineId,idCorriere);
		
		return "redirect:/corriere/dashboard";
	}
}