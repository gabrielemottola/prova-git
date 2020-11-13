package it.unibas.atleti.modello;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class Disciplina{

	//private static Logger logger = LoggerFactory.getLogger(Disciplina.class);
	private String codice;
	private String nome;
	private int etaMin;
	private int etaMax;
	private List<Atleta> listaAtleti = new ArrayList<Atleta>();

	public Disciplina(String codice, String nome, int etaMin, int etaMax){
		this.codice = codice;
		this.nome = nome;
		this.etaMin = etaMin;
		this.etaMax = etaMax;
	}


	public String getCodice(){
		return this.codice;
	}
	public String getNome(){
		return this.nome;
	}
	public int getEtaMin(){
		return this.etaMin;
	}
	public int getEtaMax(){
		return this.etaMax;
	}
	public List<Atleta> getListaAtleti(){
		return this.listaAtleti;
	}

	public void aggiungiAtleta(Atleta atleta){
		this.listaAtleti.add(atleta);
	}

	public List<Atleta> getAtletiPerNazionalita(String nazionalita){
		List<Atleta> nuovaListaAtleta = new ArrayList<Atleta>();
		for(Atleta atleta : this.listaAtleti){
			if(atleta.getNazionalita().equalsIgnoreCase(nazionalita)){
				nuovaListaAtleta.add(atleta);
			}
		}
		return nuovaListaAtleta;
	}
	public boolean controllaAtleti(String nazionalita){
		List<Atleta> listaAtletiNazionalita = this.getAtletiPerNazionalita(nazionalita);
		List<Atleta> listaAtletiNazionalitaOrdinati = new ArrayList<Atleta>();
		listaAtletiNazionalitaOrdinati.addAll(listaAtletiNazionalita);
		Collections.sort(listaAtletiNazionalitaOrdinati);
		for (int i = 0; i < listaAtletiNazionalitaOrdinati.size(); i++){
			if (listaAtletiNazionalitaOrdinati.get(i).equals(listaAtletiNazionalita.get(i))){
				return false;
			}
		}
		return true;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\n-Codice: " + this.codice +"\n");
		sb.append("-Nome: " + this.nome +"\n");
		sb.append("-Eta minima: " + this.etaMin +"\n");
		sb.append("-Eta massima: " + this.etaMax +"\n");
		sb.append("-Lista degli atleti:\n");
		for(Atleta atleta : this.listaAtleti){
			sb.append("-Atleta: " + atleta.toString()+"\n");
		}
		return sb.toString();
	}

	public int calcolaEtaMediaPerNazionalita(String nazionalita){
		List<Atleta> listaAtletiPerNazionalita = new ArrayList<Atleta>();
		listaAtletiPerNazionalita = this.getAtletiPerNazionalita(nazionalita);
		int etaMedia = 0;
		for(Atleta atleta : listaAtletiPerNazionalita){
			etaMedia = etaMedia + atleta.getEta();
		}
		//logger.debug("Eta' media per " + this.getNome() + ": " + etaMedia);
		return etaMedia;
	}

	public boolean verificaAtletiNazione(String nazionalita){
		int cont = 0;
		for(Atleta atleta : this.getListaAtleti()){
			if(atleta.getNazionalita().equals(nazionalita)){
				cont = cont + 1;
			}
		}
		//logger.debug("Nella disciplina " + this.getNome() + " ci sono " + cont + 
						//" atleti provenienti da " + nazionalita);
		if(cont >= 2){
			return true;
		}
		return false;
	}

}

