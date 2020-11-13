package it.unibas.atleti.modello;

import java.util.List;
import java.util.ArrayList;

public class Archivio{

	private List<Disciplina> listaDiscipline = new ArrayList<Disciplina>();


	public List<Disciplina> getListaDiscipline(){
		return this.listaDiscipline;
	}

	public void aggiungiDisciplina(Disciplina disciplina){
		this.listaDiscipline.add(disciplina);
	}

	public Disciplina cercaDisciplinaEsistente(String codice){
		for (Disciplina disciplina : this.listaDiscipline){
			if (codice.trim().equalsIgnoreCase(disciplina.getCodice()))
				return disciplina;
		}
		return null;
	}
	public Disciplina calcolaDisciplinaMinEtaMedia(String nazionalita){
		Disciplina disciplinaMin = null;
		for(Disciplina disciplina : this.getDisciplinePerNazionalita(nazionalita)){
			if(disciplinaMin == null ||
				 disciplina.calcolaEtaMediaPerNazionalita(nazionalita) < 
				 disciplinaMin.calcolaEtaMediaPerNazionalita(nazionalita)) {
				disciplinaMin = disciplina;   //fa direttamente il disciplinaMin == null e disciplinaMin diventa il primo elem, poi si confronts con gli altri
			}
		}
		return disciplinaMin;
	}

	public List<Disciplina> getDisciplinePerNazionalita(String nazionalita) {
		List<Disciplina> listaDisciplinePerNazionalita = new ArrayList<Disciplina>();
		for(Disciplina disciplina : this.getListaDiscipline()) {
			if(disciplina.verificaAtletiNazione(nazionalita)) {
				listaDisciplinePerNazionalita.add(disciplina);
			}
		}
		return listaDisciplinePerNazionalita;
	}
}