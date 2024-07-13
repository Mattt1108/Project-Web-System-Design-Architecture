/**
 * Questa classe rappresenta la configurazione di sicurezza per l'applicazione web.
 * Definisce le regole di autorizzazione per le diverse risorse e fornisce i bean necessari per l'autenticazione.
 */
package com.panineria.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.panineria.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Restituisce un'istanza del servizio UserDetailsService.
     *
     * @return un'istanza del servizio UserDetailsService
     */
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * Restituisce un'istanza del BCryptPasswordEncoder per l'hashing delle password.
     *
     * @return un'istanza del BCryptPasswordEncoder
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Restituisce un'istanza del DaoAuthenticationProvider per l'autenticazione degli utenti.
     *
     * @return un'istanza del DaoAuthenticationProvider
     */
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configura le regole di autorizzazione per le diverse risorse.
     *
     * @param http l'oggetto HttpSecurity per la configurazione
     * @return l'oggetto SecurityFilterChain configurato
     * @throws Exception se si verifica un errore durante la configurazione
     */
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            
                .requestMatchers("/logout/**").permitAll()
                .requestMatchers("/ordine/componiOrdine/**").permitAll()
                .requestMatchers("/api/ordine/creaOrdine/**").permitAll()
                .requestMatchers("/ordine/ordina/**").permitAll()
                .requestMatchers("/ordine/clienteRegistrato/**").hasAuthority("CLIENTEREGISTRATO")
                .requestMatchers("/cuoco/**").hasAuthority("CUOCO")
                .requestMatchers("/api/cuoco/**").hasAuthority("CUOCO")
                .requestMatchers("/addettoConsegna/**").hasAuthority("ADDETTOCONSEGNA")
                .requestMatchers("/api/addettoConsegna/**").hasAuthority("ADDETTOCONSEGNA")
                .requestMatchers("/responsabile/**").hasAuthority("RESPONSABILE")
                .requestMatchers("/api/responsabile/**").hasAuthority("RESPONSABILE")
                .requestMatchers("/corriere/**").hasAuthority("CORRIERE")
            )
            .formLogin(login -> login.permitAll())
            .logout(logout -> logout.permitAll())
            .exceptionHandling(eh -> eh.accessDeniedPage("/403"))
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
            .csrf(csrf -> csrf.disable()); // Disabilita CSRF per le API REST

        return http.build();
    }

    /**
     * Restituisce un'istanza del CorsConfigurationSource per la gestione delle configurazioni CORS.
     *
     * @return un'istanza del CorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
