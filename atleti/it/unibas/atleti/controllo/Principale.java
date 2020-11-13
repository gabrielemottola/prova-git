package it.unibas.atleti.controllo;

import it.unibas.atleti.modello.*;
import it.unibas.atleti.persistenza.*;
import it.unibas.utilita.Console;
import java.util.List;
import java.util.ArrayList;

public class Principale{

	private  int leggiSceltaNonVuota(){
		int scelta;
		scelta = Console.leggiIntero();
		while(scelta < 0 || scelta > 6){
			System.out.println("**ERRORE** inserisci un numero tra 0 e 6");
			scelta = Console.leggiIntero();
		}
		return scelta;
	}

	private  String leggiStringaNonVuota(){
		String stringa;
		stringa = Console.leggiStringa();
		while(stringa.trim().isEmpty()){
			System.out.println("**ERRORE** inserisci una stringa");
			stringa = Console.leggiStringa();
		}
		return stringa;
	}

	private int leggiEta(){
		int eta = Console.leggiIntero();
		while(eta < 0){
			System.out.println("**ERRORE** l'eta non puo essere nagativa ");
			eta = Console.leggiIntero();
		}
		return eta;
	}

	private int inserisciScelta(){
		System.out.println("+--------------------------------------------------+");
		System.out.println("|                    MENU                          |");
		System.out.println("|--------------------------------------------------|");
		System.out.println("| 1] Utente inserisce dati disciplina              |");
		System.out.println("| 2] Utente inserisce dati atleta                  |");
		System.out.println("| 3] Utente verifica ordinamento in una disciplina |");
		System.out.println("| 4] Utente cerca disciplina piu giovane           |");
		System.out.println("| 5] Utente salva dati su disco                    |");
		System.out.println("| 6] Utente carica dati da archivio                |");
		System.out.println("| 0] esci                                          |");
		System.out.println("+--------------------------------------------------+");

		int scelta;
		System.out.println("inserisci la scelta: ");
		scelta = this.leggiSceltaNonVuota();
		return scelta;
	}

	private void inserisciDatiDisciplina(Archivio archivio){
		String codice;
		String nome;
		int etaMin;
		int etaMax;
		System.out.println("inserisci i dati della nuova disciplina");
		System.out.println("Codice: ");
		codice = this.leggiStringaNonVuota();
		if (archivio.cercaDisciplinaEsistente(codice) != null ){
			System.out.println("La disciplina e' gia esistente");
			return;
		}
		System.out.println("Nome: ");
		nome = this.leggiStringaNonVuota();
		System.out.println("Eta minima: ");
		etaMin = this.leggiEta();
		System.out.println("Eta massima: ");
		etaMax = this.leggiEta();
		Disciplina nuovaDisciplina = new Disciplina(codice, nome, etaMin, etaMax);
		archivio.aggiungiDisciplina(nuovaDisciplina);
	}

	private void inserisciDatiAtleta(Archivio archivio){
		String nome;
		String cognome;
		String nazionalita;
		int eta;
		System.out.println("inserisci il codice della disciplina in cui inserire l'atleta");
		String codice = this.leggiStringaNonVuota();
		Disciplina disciplina = archivio.cercaDisciplinaEsistente(codice);
		if(disciplina == null){
			System.out.println("**ERRORE** codice non esistente");
			return;
		}
		System.out.println("RIEPILOGO DISCIPLINA:");
		System.out.println(disciplina.toString());
		System.out.println("Nome: ");
		nome = this.leggiStringaNonVuota();
		System.out.println("Cognome: ");
		cognome = this.leggiStringaNonVuota();
		System.out.println("Nazionalita: ");
		nazionalita = this.leggiStringaNonVuota();
		System.out.println("Eta: ");
		eta = this.leggiEta();
		if(eta < disciplina.getEtaMin() || eta > disciplina.getEtaMax()){
			System.out.println("**ERRORE** l'eta dell'atleta non rispetta l'eta consentita dalla disciplina");
			return;
		}
		Atleta nuovoAtleta = new Atleta(nome, cognome, nazionalita, eta);
		disciplina.aggiungiAtleta(nuovoAtleta);
	}

	private void schermoOrdinDisciplina(Archivio archivio){
		System.out.println("inserisci il codice di una disciplina");
		String codice = this.leggiStringaNonVuota();
		Disciplina disciplina = archivio.cercaDisciplinaEsistente(codice);
		if(disciplina == null){
			System.out.println("**ERRORE** codice non esistente");
			return;
		}
		System.out.println("inserisci la nazionalita");
		String nazionalita = this.leggiStringaNonVuota();
		if(disciplina.getAtletiPerNazionalita(nazionalita).size() == 0) {
			System.out.println("nella disciplina " + disciplina + " non ci sono atleti della nazione " + nazionalita);
			return;
		}
		if (disciplina.controllaAtleti(nazionalita) == true){
			System.out.println("nella disciplina " + disciplina + " gli atleti della nazione " 
								+ nazionalita + " sono stati inseriti in ordine crescente");
		}else{
			System.out.println("nella disciplina " + disciplina + " gli atleti della nazione " 
								+ nazionalita + " non sono stati inseriti in ordine crescente");

		}
	}

	private void schermoDisciplinaGiovane(Archivio archivio){
		System.out.println("Nazionalita: ");
		String nazionalita = this.leggiStringaNonVuota();
		List<Disciplina> listaDiscipline = new ArrayList<Disciplina>();
		listaDiscipline = archivio.getDisciplinePerNazionalita(nazionalita);
		Disciplina disciplinaMin = archivio.calcolaDisciplinaMinEtaMedia(nazionalita);
		System.out.println("La disciplina con l'eta media minima con atleti di nazionalita " 
							+ nazionalita + " e' " + disciplinaMin);
	}

	private void schermoSalvaArchivio(Archivio archivio){
		System.out.println("Inserisci il nome del file");
		String nomeFile = this.leggiStringaNonVuota();

		IDAOArchivio daoArchivio = new DAOArchivioFile();
		try {
			daoArchivio.salva(archivio, nomeFile);
		} catch(DAOException e) {
			System.out.println("Impossibile salvare l'archivio sul file: " + e.getMessage());
			return;
		}
	}

	private void caricaDaFile(Archivio archivio, String nomeFile){
		
	}

	private void esegui(){
		Archivio archivio = new Archivio();
		while(true){
			int scelta = inserisciScelta();
			if (scelta == 0){
				break;
			}else if(scelta == 1){
				this.inserisciDatiDisciplina(archivio);
			}else if(scelta == 2){
				this.inserisciDatiAtleta(archivio);
			}else if(scelta == 3){
				this.schermoOrdinDisciplina(archivio);
			}else if(scelta == 4){
				this.schermoDisciplinaGiovane(archivio);
			}else if(scelta == 5){
				this.schermoSalvaArchivio(archivio);
			}else if(scelta == 6){
				this.caricaDaFile(archivio, nomeFile);
			}

		}
	}

	public static void main(String[] args) {
		Principale p = new Principale();
		p.esegui();
	}

}

