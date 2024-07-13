package com.panineria.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panineria.demo.model.Bibita;
import com.panineria.demo.model.Contorno;
import com.panineria.demo.model.Panino;
import com.panineria.demo.repository.PaninoRepository;
import com.panineria.demo.repository.BibitaRepository;
import com.panineria.demo.repository.ContornoRepository;

/**
 * Questa classe rappresenta il servizio del menu della panineria.
 * Gestisce le operazioni relative ai panini, bibite e contorni.
 */
@Service
public class MenuService 
{
	@Autowired
	PaninoRepository paninoRepository ;
	@Autowired
	BibitaRepository bibitaRepository ;
	@Autowired
	ContornoRepository contornoRepository ;
	
	/**
	 * Restituisce tutti i panini presenti nel menu.
	 * 
	 * @return un'iterabile di oggetti Panino
	 */
	public Iterable<Panino> getAllPanini()
	{
		return paninoRepository.findAll();
	}
	
	/**
	 * Restituisce il panino corrispondente all'ID specificato.
	 * 
	 * @param id l'ID del panino
	 * @return un Optional contenente il panino, se presente
	 */
	public Optional<Panino> getPaninoById(Integer id)
	{
		return paninoRepository.findById(id);
	}
	
	/**
	 * Restituisce la bibita corrispondente all'ID specificato.
	 * 
	 * @param id l'ID della bibita
	 * @return un Optional contenente la bibita, se presente
	 */
	public Optional<Bibita> getBibitaById(Integer id)
	{
		return bibitaRepository.findById(id);
	}
	
	/**
	 * Restituisce tutte le bibite presenti nel menu.
	 * 
	 * @return un'iterabile di oggetti Bibita
	 */
	public Iterable<Bibita> getAllBibite()
	{
		return bibitaRepository.findAll();
	}
	
	/**
	 * Restituisce tutti i contorni presenti nel menu.
	 * 
	 * @return un'iterabile di oggetti Contorno
	 */
	public Iterable<Contorno> getAllContorni()
	{
		return contornoRepository.findAll();
	}
	
	/**
	 * Restituisce il contorno corrispondente all'ID specificato.
	 * 
	 * @param id l'ID del contorno
	 * @return un Optional contenente il contorno, se presente
	 */
	public Optional<Contorno> getContornoById(Integer id)
	{
		return contornoRepository.findById(id);
	}
	
	/**
	 * Restituisce il panino corrispondente al nome specificato.
	 * 
	 * @param nome il nome del panino
	 * @return un Optional contenente il panino, se presente
	 */
	public Optional<Panino> getPaninoByNome(String nome)
	{
		return paninoRepository.findByNome(nome);
	}
	
	/**
	 * Restituisce la bibita corrispondente al nome specificato.
	 * 
	 * @param nome il nome della bibita
	 * @return un Optional contenente la bibita, se presente
	 */
	public Optional<Bibita> getBibitaByNome(String nome)
	{
		return bibitaRepository.findByNome(nome);
	}
	
	/**
	 * Restituisce il contorno corrispondente al nome specificato.
	 * 
	 * @param nome il nome del contorno
	 * @return un Optional contenente il contorno, se presente
	 */
	public Optional<Contorno> getContornoByNome(String nome)
	{
		return contornoRepository.findByNome(nome);
	}
}
