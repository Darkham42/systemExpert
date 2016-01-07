package sysexp.modele;

/**
 * 
 * @author Darkham and Tanaky
 * Classe Generique representant un fait de la base de connaissance
 * @param <E> Le type de fait
 */
public abstract class Fait<E> {
	protected String symbole;
	protected E valeur;
	
	/**
	 * 
	 * Constructeur du Fait
	 * @param symbole le chaine de caractere representant le Fait
	 */
	public Fait(String symbole) {
		this.symbole = symbole;
	}
	
	/**
	 * Mutateur du Fait
	 * @param valeur La valeur Generique du Fait
	 */
	public void mettreAJourValeur(E valeur){
		this.valeur = valeur;
	}
	
	/**
	 *	Accesseur du Fait
	 * @return La valeur Generique du Fait
	 */
	public E lireValeur(){
		return this.valeur;
	}
	
	/**
	 * Accesseur du Symbole representant le Fait
	 * @return
	 */
	public String lireSymbole(){
		return this.symbole;
	}
	
	/**
	 * Verifie si 2 Faits sont identiques
	 * @param fait Le Fait a comparer
	 * @return
	 */
	public boolean egale(Fait<E> fait){
		return this.valeur.equals(fait.lireValeur()) && this.symbole.equals(fait.symbole);
	}
	
	public String toString(){
		return this.symbole + "(" + this.valeur + ");";
	}
}
