package com.panineria.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.ClienteRegistrato;
import com.panineria.demo.repository.ClienteRegistratoRepository;

/**
 * Questa classe rappresenta il servizio per la gestione dei clienti registrati.
 */
@Service
public class ClienteRegistratoService 
{
	@Autowired
	ClienteRegistratoRepository clienteRegistratoRepository;

	/**
	 * Restituisce un cliente registrato in base all'ID specificato.
	 * 
	 * @param idClienteRegistrato l'ID del cliente registrato
	 * @return un Optional contenente il cliente registrato, se presente
	 */
	public Optional<ClienteRegistrato> getClienteRegistratoById(Integer idClienteRegistrato) 
	{
		return clienteRegistratoRepository.findById(idClienteRegistrato);
	}
	
}
