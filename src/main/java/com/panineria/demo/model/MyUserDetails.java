package com.panineria.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Questa classe rappresenta i dettagli dell'utente personalizzati per l'autenticazione e l'autorizzazione.
 * Implementa l'interfaccia UserDetails di Spring Security.
 */
public class MyUserDetails implements UserDetails {

    private Utente utente;

    /**
     * Costruttore della classe MyUserDetails.
     * 
     * @param utente l'utente per cui creare i dettagli personalizzati
     */
    public MyUserDetails(Utente utente) {
        this.utente = utente;
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        authorities.add(new SimpleGrantedAuthority(utente.getRuolo()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
