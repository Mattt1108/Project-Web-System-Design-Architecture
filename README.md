# Sistema Distribuito per la Gestione di Ordini, Produzione e Consegna Panini

## Overview
Questo progetto è stato sviluppato come parte del corso "Web Systems Design and Architecture" per l'anno accademico 2024-25. L'obiettivo è creare un sistema distribuito per la gestione degli ordini, la preparazione e la consegna dei panini. Il sistema è sviluppato utilizzando Spring Boot e segue un'architettura basata su servizi web.

## Funzionalità Principali

### Utente (Cliente Non Registrato)
- **Inserimento Ordini:** Gli utenti possono accedere alla pagina degli ordini senza effettuare il login, comporre un ordine (panino, contorni, bibite, extra), simulare un pagamento e inoltrare l'ordine. 
- **Ricezione Codice Ritiro:** Dopo l'inoltro, il sistema restituirà un codice di ritiro.

### Backend
- **Gestione Ordini:** Gli ordini vengono inviati al backend in formato JSON, memorizzati in un database relazionale e marcati come "da lavorare".

### Cuoco
- **Login:** Accesso tramite login.
- **Gestione Ordini:** Pagina per richiedere il prossimo ordine da preparare. Gli ordini vengono marcati come "in lavorazione" e, una volta completati, come "al banco".

### Addetto Consegna
- **Login:** Accesso tramite login.
- **Gestione Ordini:** Pagina per visualizzare gli ordini "al banco" e marcare gli ordini come "completati".

### Opzioni Aggiuntive
- **Tracciamento Temporale:** Memorizzazione dei timestamp dei vari passaggi degli ordini nel database.

### Responsabile
- **Login:** Accesso tramite login.
- **Statistiche:** Pagina per calcolare statistiche di lavorazione (tempo medio di preparazione, consegna, attesa, tempo medio di preparazione dei singoli cuochi, ecc.).

### Cliente Registrato
- **Login:** Accesso tramite login.
- **Gestione Ordini:** Accesso alla pagina degli ordini per fare ordini e richiedere la consegna a domicilio. Gli ordini passano allo stato "domicilio" dopo la preparazione.

### Corriere
- **Login:** Accesso tramite login.
- **Gestione Consegne:** Pagina per richiedere ordini da consegnare a domicilio, confermare la presa in carico (stato "in consegna") e marcare l'ordine come "consegnato".

## Installazione e Configurazione
1. **Clone Repository:** `git clone https://github.com/Mattt1108/Project-Web-System-Design-Architecture.git`
2. **Configura Database:** Imposta le credenziali del database in `src/main/resources/application.properties`.
3. **Build and Run:** Usa Maven per buildare e avviare l'applicazione:

   ```sh
   mvn clean install
   mvn spring-boot:run
