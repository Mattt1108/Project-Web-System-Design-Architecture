
package com.panineria.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.panineria.demo.model.MyUserDetails;
import com.panineria.demo.model.Utente;
import com.panineria.demo.repository.UtenteRepository;

/**
 * Questa classe implementa l'interfaccia UserDetailsService di Spring Security
 * per fornire i dettagli dell'utente durante l'autenticazione.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UtenteRepository utenteRepository;

    /**
     * Carica i dettagli dell'utente in base al nome utente fornito.
     * 
     * @param username il nome utente dell'utente da caricare
     * @return i dettagli dell'utente
     * @throws UsernameNotFoundException se l'utente non viene trovato
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<Utente> utenteOptional = utenteRepository.findByUsername(username);
        
        Utente utente = utenteOptional.orElseThrow(() -> new UsernameNotFoundException("Utente non riconosciuto"));
        return new MyUserDetails(utente);
    }

}