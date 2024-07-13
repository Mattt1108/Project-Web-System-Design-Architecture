package com.panineria.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * La classe PanineriaApplication è la classe principale dell'applicazione Panineria.
 * Questa classe avvia l'applicazione Spring Boot.
 */
@SpringBootApplication
public class PanineriaApplication {

	/**
	 * Il metodo main è il punto di ingresso dell'applicazione.
	 * Viene chiamato quando l'applicazione viene avviata.
	 * @param args gli argomenti della riga di comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(PanineriaApplication.class, args);
	}

}
