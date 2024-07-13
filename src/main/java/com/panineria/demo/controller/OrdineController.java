package com.panineria.demo.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panineria.demo.model.ClienteRegistrato;
import com.panineria.demo.model.Ordine;
import com.panineria.demo.service.ClienteRegistratoService;
import com.panineria.demo.service.MenuService;
import com.panineria.demo.service.OrdineService;
import com.panineria.demo.service.UtenteService;



/**
 * Classe controller per la gestione degli ordini.
 */
@Controller
@RequestMapping("/ordine")
public class OrdineController {

	@Autowired
	private ClienteRegistratoService clienteRegistratoService;
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private OrdineService ordineService;
	@Autowired
	private MenuService menuService;

	/**
	 * Gestisce la richiesta GET per l'endpoint "/ordine/ordina".
	 * 
	 * @param model l'oggetto modello
	 * @return il nome della vista "ordina"
	 */
	@GetMapping("/ordina")
	public String ordina(Model model) {
		return "ordina";
	}

	/**
	 * Gestisce la richiesta GET per l'endpoint "/ordine/componiOrdine".
	 * 
	 * @param model l'oggetto modello
	 * @return il nome della vista "componiOrdine"
	 */
	@GetMapping("/componiOrdine")
	public String componiOrdine(Model model) {
		model.addAttribute("panini", menuService.getAllPanini());
		model.addAttribute("contorni", menuService.getAllContorni());
		model.addAttribute("bibite", menuService.getAllBibite());
		return "componiOrdine";
	}

	/**
	 * Gestisce la richiesta GET per l'endpoint "/ordine/clienteRegistrato/componiOrdine".
	 * 
	 * @param model l'oggetto modello
	 * @return il nome della vista "componiOrdineClienteRegistrato"
	 */
	@GetMapping("/clienteRegistrato/componiOrdine")
	public String ClienteRegistratoComponiOrdine(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Integer idClienteRegistrato = utenteService.getUtenteByUsername(userDetails.getUsername()).get().getId();
		ClienteRegistrato cliente = clienteRegistratoService.getClienteRegistratoById(idClienteRegistrato).get();
	
		model.addAttribute("cliente", cliente);
		model.addAttribute("ordine", new Ordine());
		model.addAttribute("panini", menuService.getAllPanini());
		model.addAttribute("contorni", menuService.getAllContorni());
		model.addAttribute("bibite", menuService.getAllBibite());
		return "componiOrdineClienteRegistrato";
	}

	/**
	 * Gestisce la richiesta POST per l'endpoint "/ordine/clienteRegistrato/submitComponiOrdine".
	 * 
	 * @param ordine l'oggetto ordine
	 * @param model  l'oggetto modello
	 * @return il nome della vista "resocontoClienteRegistrato"
	 */
	@PostMapping("/clienteRegistrato/submitComponiOrdine")
	public String resoconto(Ordine ordine, Model model) {
		ordine.setStato("DA_LAVORARE");
		ordine.setTimestamp_DA_LAVORARE(LocalDateTime.now());
		ordine.genCodiceRitiro();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Integer idClienteRegistrato = utenteService.getUtenteByUsername(userDetails.getUsername()).get().getId();
		ordine.setIdCliente(idClienteRegistrato);
		ClienteRegistrato clienteRegistrato = clienteRegistratoService.getClienteRegistratoById(ordine.getIdCliente()).get();
		ordine.setIndirizzo(clienteRegistrato.getIndirizzo());
		ordine.setRecapito(clienteRegistrato.getRecapito());
		Double costoTotale;
		costoTotale = menuService.getBibitaByNome(ordine.getBibita()).get().getPrezzo();
		costoTotale += menuService.getPaninoByNome(ordine.getPanino()).get().getPrezzo();
		costoTotale += menuService.getContornoByNome(ordine.getContorno()).get().getPrezzo();
		ordine.setCostoTotale(costoTotale);

		ordineService.create(ordine);
		model.addAttribute("ordine", ordine);

		return "resocontoClienteRegistrato";
	}
}
