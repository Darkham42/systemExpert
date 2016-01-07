package sysexp.modele;

public abstract class FormeSymbolique extends Forme {
	private String valeur;
	private Comparateur comparateur;
	public FormeSymbolique(String fait, Comparateur comparateur, String valeur) {
		super(fait);
		this.valeur = valeur;
		this.comparateur = comparateur;
	}
	
	public String toString(){
		return this.lireFait() + " " + comparateur + " " + valeur;
	}
	
	public Comparateur lireComparateur(){
		return this.comparateur;
	}
	
	public String lireValeur(){
		return this.valeur;
	}
}
