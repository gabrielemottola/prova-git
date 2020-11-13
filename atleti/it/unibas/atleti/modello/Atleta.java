package it.unibas.atleti.modello;

public class Atleta implements Comparable<Atleta>{
	private String nome;
	private String cognome;
	private String nazionalita;
	private int eta;

	public Atleta(String nome, String cognome, String nazionalita, int eta){
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.eta = eta;
	}

	public String getNome(){
		return this.nome;
	}
	public String getCognome(){
		return this.cognome;
	}
	public String getNazionalita(){
		return this.nazionalita;
	}
	public int getEta(){
		return this.eta;
	}
	//questo.getEta() = 50
	//altroAtleta.getEta() = 55
	//50 - 55 < 0
	public int compareTo(Atleta altroAtleta){                  //lo usa Collections.sort()
		return (this.getEta() - altroAtleta.getEta());		  //dobbiamo ordinare in base all'eta
	}

	public boolean equals(Atleta atleta){
		if(this.nome.equals(atleta.getNome()) && this.cognome.equals(atleta.getCognome()) && 
			this.nazionalita.equals(atleta.getNazionalita()) && this.eta == atleta.getEta()){
			return true;
		}
		return false;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\n~Nome: " + this.nome +"\n");
		sb.append("~Cognome: " + this.cognome +"\n");
		sb.append("~Nazionalita: " + this.nazionalita +"\n");
		sb.append("~Eta: " + this.eta );
		return sb.toString();
	}
}