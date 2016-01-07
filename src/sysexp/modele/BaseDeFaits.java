package sysexp.modele;

import java.util.HashMap;

public class BaseDeFaits {
	private HashMap<String, Fait> dictionnaire;
	
	public BaseDeFaits() {
		dictionnaire = new HashMap<String, Fait>();
	}
	
	public Fait rechercher(String nom){
		return this.dictionnaire.get(nom);
	}
	
	public boolean existe(String nom){
		return this.dictionnaire.containsKey(nom);
	}
	
	public void ajouter(String nom, Fait fait){
		this.dictionnaire.put(nom, fait);
	}
	
	public String toString(){
		String affichage = "Base De Fait:";
		for(Fait fait : this.dictionnaire.values() ){
			affichage += "\n\t" + fait.toString();
		}
		return affichage;
	}
}
