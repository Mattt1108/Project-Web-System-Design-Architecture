package com.panineria.demo.model;

import java.time.LocalDateTime;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Questa classe rappresenta un ordine effettuato presso la panineria.
 */
@Entity
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String panino;
    
    private String contorno;
    
    private String bibita;
    
    private String extra;
    
    private String stato;
    
    private String codiceRitiro; //ID + prima lettera panino + numero random 1-9999
    
    private Integer idCuoco;
    
    private Integer idAddettoConsegna;
    
    private LocalDateTime timestamp_DA_LAVORARE;
    private LocalDateTime timestamp_IN_LAVORAZIONE;
    private LocalDateTime timestamp_AL_BANCO;
    private LocalDateTime timestamp_COMPLETATO;
    private LocalDateTime timestamp_CONSEGNA;

	private String indirizzo;
	private String recapito; //numero di telefono
    
    private Integer idCliente;
    
    private Double costoTotale;
    
    /**
     * Restituisce l'indirizzo di consegna dell'ordine.
     * 
     * @return l'indirizzo di consegna dell'ordine
     */
    public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Imposta l'indirizzo di consegna dell'ordine.
	 * 
	 * @param indirizzo l'indirizzo di consegna dell'ordine
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Restituisce il recapito telefonico del cliente che ha effettuato l'ordine.
	 * 
	 * @return il recapito telefonico del cliente
	 */
	public String getRecapito() {
		return recapito;
	}

	/**
	 * Imposta il recapito telefonico del cliente che ha effettuato l'ordine.
	 * 
	 * @param recapito il recapito telefonico del cliente
	 */
	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	/**
	 * Restituisce l'ID dell'ordine.
	 * 
	 * @return l'ID dell'ordine
	 */
	public Integer getId() {
        return id;
    }

    /**
     * Restituisce il timestamp dell'ordine quando è in stato "DA LAVORARE".
     * 
     * @return il timestamp dell'ordine quando è in stato "DA LAVORARE"
     */
    public LocalDateTime getTimestamp_DA_LAVORARE() {
		return timestamp_DA_LAVORARE;
	}

	/**
	 * Imposta il timestamp dell'ordine quando è in stato "DA LAVORARE".
	 * 
	 * @param timestamp_DA_LAVORARE il timestamp dell'ordine quando è in stato "DA LAVORARE"
	 */
	public void setTimestamp_DA_LAVORARE(LocalDateTime timestamp_DA_LAVORARE) {
		this.timestamp_DA_LAVORARE = timestamp_DA_LAVORARE;
	}

	/**
	 * Imposta l'ID dell'ordine.
	 * 
	 * @param id l'ID dell'ordine
	 */
	public void setId(Integer id) {
        this.id = id;
    }

	/**
	 * Restituisce il nome del panino dell'ordine.
	 * 
	 * @return il nome del panino dell'ordine
	 */
	public String getPanino() {
		return panino;
	}

	/**
	 * Imposta il nome del panino dell'ordine.
	 * 
	 * @param panino il nome del panino dell'ordine
	 */
	public void setPanino(String panino) {
		this.panino = panino;
	}

	/**
	 * Restituisce il nome del contorno dell'ordine.
	 * 
	 * @return il nome del contorno dell'ordine
	 */
	public String getContorno() {
		return contorno;
	}

	/**
	 * Imposta il nome del contorno dell'ordine.
	 * 
	 * @param contorno il nome del contorno dell'ordine
	 */
	public void setContorno(String contorno) {
		this.contorno = contorno;
	}

	/**
	 * Restituisce il nome della bibita dell'ordine.
	 * 
	 * @return il nome della bibita dell'ordine
	 */
	public String getBibita() {
		return bibita;
	}

	/**
	 * Imposta il nome della bibita dell'ordine.
	 * 
	 * @param bibita il nome della bibita dell'ordine
	 */
	public void setBibita(String bibita) {
		this.bibita = bibita;
	}

	/**
	 * Restituisce il nome dell'extra dell'ordine.
	 * 
	 * @return il nome dell'extra dell'ordine
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * Imposta il nome dell'extra dell'ordine.
	 * 
	 * @param extra il nome dell'extra dell'ordine
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * Restituisce lo stato dell'ordine.
	 * 
	 * @return lo stato dell'ordine
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Imposta lo stato dell'ordine.
	 * 
	 * @param stato lo stato dell'ordine
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Genera il codice di ritiro dell'ordine.
	 * Il codice di ritiro è composto dall'ID dell'ordine, dalla prima lettera del panino e da un numero casuale compreso tra 1 e 9999.
	 */
	public void genCodiceRitiro()
	{
		// Genera un numero casuale tra 1 e 9999
		Random random = new Random();
        int numero = random.nextInt(9999) + 1;
		
		this.codiceRitiro=""+this.panino.charAt(0)+this.bibita.charAt(0)+numero;  // prima lettera panino + prima lettera bibita  +  numero random 
		
	}
	
	/**
	 * Restituisce il codice di ritiro dell'ordine.
	 * 
	 * @return il codice di ritiro dell'ordine
	 */
	public String getCodiceRitiro()
	{
		return this.codiceRitiro;
	}

	/**
	 * Restituisce il timestamp dell'ordine quando è in stato "IN LAVORAZIONE".
	 * 
	 * @return il timestamp dell'ordine quando è in stato "IN LAVORAZIONE"
	 */
	public LocalDateTime getTimestamp_IN_LAVORAZIONE() {
		return timestamp_IN_LAVORAZIONE;
	}

	/**
	 * Imposta il timestamp dell'ordine quando è in stato "IN LAVORAZIONE".
	 * 
	 * @param timestamp_IN_LAVORAZIONE il timestamp dell'ordine quando è in stato "IN LAVORAZIONE"
	 */
	public void setTimestamp_IN_LAVORAZIONE(LocalDateTime timestamp_IN_LAVORAZIONE) {
		this.timestamp_IN_LAVORAZIONE = timestamp_IN_LAVORAZIONE;
	}

	/**
	 * Restituisce l'ID del cuoco che sta preparando l'ordine.
	 * 
	 * @return l'ID del cuoco che sta preparando l'ordine
	 */
	public Integer getIdCuoco() {
		return idCuoco;
	}

	/**
	 * Imposta l'ID del cuoco che sta preparando l'ordine.
	 * 
	 * @param idCuoco l'ID del cuoco che sta preparando l'ordine
	 */
	public void setIdCuoco(Integer idCuoco) {
		this.idCuoco = idCuoco;
	}

	/**
	 * Restituisce il timestamp dell'ordine quando è al banco.
	 * 
	 * @return il timestamp dell'ordine quando è al banco
	 */
	public LocalDateTime getTimestamp_AL_BANCO() {
		return timestamp_AL_BANCO;
	}

	/**
	 * Imposta il timestamp dell'ordine quando è al banco.
	 * 
	 * @param timestamp_AL_BANCO il timestamp dell'ordine quando è al banco
	 */
	public void setTimestamp_AL_BANCO(LocalDateTime timestamp_AL_BANCO) {
		this.timestamp_AL_BANCO = timestamp_AL_BANCO;
	}

	/**
	 * Restituisce il timestamp dell'ordine quando è completato.
	 * 
	 * @return il timestamp dell'ordine quando è completato
	 */
	public LocalDateTime getTimestamp_COMPLETATO() {
		return timestamp_COMPLETATO;
	}

	/**
	 * Imposta il timestamp dell'ordine quando è completato.
	 * 
	 * @param timestamp_COMPLETATO il timestamp dell'ordine quando è completato
	 */
	public void setTimestamp_COMPLETATO(LocalDateTime timestamp_COMPLETATO) {
		this.timestamp_COMPLETATO = timestamp_COMPLETATO;
	}

	/**
	 * Restituisce l'ID dell'addetto alla consegna dell'ordine.
	 * 
	 * @return l'ID dell'addetto alla consegna dell'ordine
	 */
	public Integer getIdAddettoConsegna() {
		return idAddettoConsegna;
	}

	/**
	 * Imposta l'ID dell'addetto alla consegna dell'ordine.
	 * 
	 * @param idAddettoConsegna l'ID dell'addetto alla consegna dell'ordine
	 */
	public void setIdAddettoConsegna(Integer idAddettoConsegna) {
		this.idAddettoConsegna = idAddettoConsegna;
	}

	/**
	 * Restituisce l'ID del cliente che ha effettuato l'ordine.
	 * 
	 * @return l'ID del cliente che ha effettuato l'ordine
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * Imposta l'ID del cliente che ha effettuato l'ordine.
	 * 
	 * @param idCliente l'ID del cliente che ha effettuato l'ordine
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Restituisce il timestamp dell'ordine quando è consegnato.
	 * 
	 * @return il timestamp dell'ordine quando è consegnato
	 */
	public LocalDateTime getTimestamp_CONSEGNA() {
		return timestamp_CONSEGNA;
	}

	/**
	 * Imposta il timestamp dell'ordine quando è consegnato.
	 * 
	 * @param timestamp_CONSEGNA il timestamp dell'ordine quando è consegnato
	 */
	public void setTimestamp_CONSEGNA(LocalDateTime timestamp_CONSEGNA) {
		this.timestamp_CONSEGNA = timestamp_CONSEGNA;
	}

	/**
	 * Restituisce il costo totale dell'ordine.
	 * 
	 * @return il costo totale dell'ordine
	 */
	public Double getCostoTotale() {
		return costoTotale;
	}

	/**
	 * Imposta il costo totale dell'ordine.
	 * 
	 * @param costoTotale il costo totale dell'ordine
	 */
	public void setCostoTotale(Double costoTotale) {
		this.costoTotale = costoTotale;
	}
    
}
