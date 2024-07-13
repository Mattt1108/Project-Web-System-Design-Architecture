package com.panineria.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panineria.demo.service.AddettoConsegnaService;
import com.panineria.demo.service.CorriereService;
import com.panineria.demo.service.CuocoService;
import com.panineria.demo.service.OrdineService;
import com.panineria.demo.service.UtenteService;

/**
 * Questa classe rappresenta il controller per le operazioni del responsabile.
 */
@Controller
@RequestMapping("/responsabile")
public class ResponsabileController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private AddettoConsegnaService addettoConsegnaService;
    @Autowired
    private CuocoService cuocoService;
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private CorriereService corriereService;

    /**
     * Metodo che gestisce la richiesta per la dashboard del responsabile.
     * 
     * @param model il modello per la vista
     * @return la stringa che rappresenta il nome della vista da visualizzare
     */
    @GetMapping("/dashboard")
    public String cuocoDashboard(Model model) {

        // prendo id da utente loggato
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Integer idResponsabile = utenteService.getUtenteByUsername(userDetails.getUsername()).get().getId();

        // STATISTICHE
        // tempo medio per cuoco
        Map<Integer, Double> TmedioPreparazioneCuoco = cuocoService.tempoMedioPreparazioneCuoco();
        // tempo medio per AddettoConsegna
        Map<Integer, Double> TmedioConsegnaAddetto = addettoConsegnaService.tempoMedioConsegnaAddettoConsegna();
        // tempo medio per Corriere
        Map<Integer, Double> TmedioConsegnaCorriere = corriereService.tempoMedioConsegnaCorriere();

        // tempo medio consegna da banco
        Double TmedioConsegnaBanco = ordineService.getTMedioConsegnaBanco();
        // tempo medio preparazione
        Double TmedioPreparazione = ordineService.getTMedioPreparazione();
        // tempo medio consegna domicilio
        Double TmedioConsegnaDomicilio = ordineService.getTMedioConsegnaCorriere();

        // tempo attesa
        Double TmedioAttesa = ordineService.getTMedioAttesa();

        model.addAttribute("TmedioConsegnaCorriere", TmedioConsegnaCorriere);
        model.addAttribute("TmedioPreparazioneCuoco", TmedioPreparazioneCuoco);
        model.addAttribute("TmedioConsegnaAddettoConsegna", TmedioConsegnaAddetto);

        model.addAttribute("idResponsabile", idResponsabile);
        model.addAttribute("TmedioConsegnaBanco", TmedioConsegnaBanco);
        model.addAttribute("TmedioPreparazione", TmedioPreparazione);
        model.addAttribute("TmedioConsegnaDomicilio", TmedioConsegnaDomicilio);
        model.addAttribute("TmedioAttesa", TmedioAttesa);
        return "responsabileDashboard";
    }
}
