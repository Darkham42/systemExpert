package sysexp.builders.lorraine;

public class Jeton {
	private final String representation;
	private final Type type;
	
	/**
     * Constructeur logique.
     *
     * @param type la valeur de {@link Jeton#type}.
     * @param representation la valeur de {@link Jeton#representation}.
     */
	protected Jeton(Type type, String representation) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.representation = representation;
	}
	
	 /**
     * Accesseur.
     *
     * @return la valeur de {@link Jeton#type}.
     */
	public Type lireType(){
		return this.type;
	}
	
	/**
     * Accesseur.
     *
     * @return la valeur de {@link Jeton#representation}.
     */
	public String lireRepresentation(){
		return this.representation;
	}
	
	/**
     * Indique si ce jeton est associe a un point virgule
     *
     * @return true si ce jeton est associe a un point virgule 
     *   sinon false.
     */
	public boolean estPointVirgule() {
		return type == Type.pointVirgule;
	}

	/**
     * Indique si ce jeton est associe a une virgule
     *
     * @return true si ce jeton est associe a une virgule 
     *   sinon false.
     */
	public boolean estVirgule() {
		return type == Type.virgule;
	}

	/**
     * Indique si ce jeton est associe au signe egale
     *
     * @return true si ce jeton est associe au signe egale 
     *   sinon false.
     */
	public boolean estEgale() {
		return type == Type.egale;
	}

	/**
     * Indique si ce jeton est associe au signe different
     *
     * @return true si ce jeton est associe au signe different 
     *   sinon false.
     */
	public boolean estDifferent() {
		return type == Type.different;
	}

	/**
     * Indique si ce jeton est associe au signe inferieur ou egale
     *
     * @return true si ce jeton est associe au signe inferieur ou egale 
     *   sinon false.
     */
	public boolean estInferieurOuEgale() {
		return type == Type.inferieurOuEgale;
	}

	/**
     * Indique si ce jeton est associe au signe superieur ou egale
     *
     * @return true si ce jeton est associe au signe superieur ou egale 
     *   sinon false.
     */
	public boolean estSuperieurOuEgale() {
		return type == Type.superieurOuEgale;
	}

	/**
     * Indique si ce jeton est associe au signe superieur
     *
     * @return true si ce jeton est associe au signe superieur 
     *   sinon false.
     */
	public boolean estSuperieur() {
		return type == Type.superieur;
	}

	/**
     * Indique si ce jeton est associe au signe inferieur
     *
     * @return true si ce jeton est associe au signe inferieur 
     *   sinon false.
     */
	public boolean estInferieur() {
		return type == Type.inferieur;
	}

	/**
     * Indique si ce jeton est associe au mot cle non
     *
     * @return true si ce jeton est associe au mot cle non 
     *   sinon false.
     */
	public boolean estNon() {
		return type == Type.non;
	}

	/**
     * Indique si ce jeton est associe au mot cle si
     *
     * @return true si ce jeton est associe au mot cle si 
     *   sinon false.
     */
	public boolean estSi() {
		return type == Type.si;
	}
	
	/**
     * Indique si ce jeton est associe au mot cle et
     *
     * @return true si ce jeton est associe au mot cle et 
     *   sinon false.
     */
	public boolean estEt() {
		return type == Type.et;
	}

	/**
     * Indique si ce jeton est associe au mot cle alors
     *
     * @return true si ce jeton est associe au mot cle alors 
     *   sinon false.
     */
	public boolean estAlors() {
		return type == Type.alors;
	}

	/**
     * Indique si ce jeton est associe a l'operateur moins
     *
     * @return true si ce jeton est associe a l'operateur moins 
     *   sinon false.
     */
	public boolean estOperateurMoins() {
		return type == Type.operateurMoins;
	}

	/**
     * Indique si ce jeton est associe a l'operateur plus
     *
     * @return true si ce jeton est associe a l'operateur plus 
     *   sinon false.
     */
	public boolean estOperateurPlus() {
		return type == Type.operateurPlus;
	}

	/**
     * Indique si ce jeton est associe a l'operateur de multiplication
     *
     * @return true si ce jeton est associe a l'operateur de multiplication 
     *   sinon false.
     */
	public boolean estOperateurMultiplie() {
		return type == Type.operateurMultiplie;
	}

	/**
     * Indique si ce jeton est associe a l'operateur divise
     *
     * @return true si ce jeton est associe a l'operateur divise 
     *   sinon false.
     */
	public boolean estOperateurDivise() {
		return type == Type.operateurDivise;
	}

	/**
     * Indique si ce jeton est associe a la parenthese ouvrante
     *
     * @return true si ce jeton est associe a la parenthese ouvrante 
     *   sinon false.
     */
	public boolean estParentheseOuvrante() {
		return type == Type.parentheseOuvrante;
	}

	/**
     * Indique si ce jeton est associe a la parenthese fermante
     *
     * @return true si ce jeton est associe a la parenthese fermante 
     *   sinon false.
     */
	public boolean estParentheseFermante() {
		return type == Type.parentheseFermante;
	}
	
	/**
     * Indique si ce jeton est associe au zero
     *
     * @return true si ce jeton est associe au zero 
     *   sinon false.
     */
	public boolean estZero() {
		return type == Type.zero;
	}
	
	/**
     * Indique si ce jeton est associe a un chiffre entier
     *
     * @return true si ce jeton est associe a un chiffre entier
     * 	 sinon false
     */
	public boolean estChiffrePositif() {
		return type == Type.chiffrePositif;
	}
	
	/**
     * Indique si ce jeton est associe a un fait booleen
     *
     * @return true si ce jeton est associe a un fait booleen
     * 	 sinon false
     */
	public boolean estFaitsBooleen() {
		return type == Type.faitsBooleen;
	}
	
	/**
     * Indique si ce jeton est associe a un fait symbolique
     *
     * @return true si ce jeton est associe a un fait symbolique
     * 	 sinon false
     */
	public boolean estFaitsSymbolique() {
		return type == Type.faitsSymbolique;
	}
	
	/**
     * Indique si ce jeton est associe a un fait entier
     *
     * @return true si ce jeton est associe a un fait entier
     * 	 sinon false
     */
	public boolean estFaitsEntier(){
		return type == Type.faitsEntier;
	}
	
	/**
     * Indique si ce jeton est associe a un tire du bas
     *
     * @return true si ce jeton est associe a un tire du bas
     * 	 sinon false
     */
	public boolean estTirerDuBas() {
		return type == Type.tirerDuBas;
	}
	
	/**
     * Indique si ce jeton est associe a une fin d'expression
     *
     * @return true si ce jeton est associe a une fin d'expression
     * 	 sinon false
     */
	public boolean estFinExpression() {
		return type == Type.finExpression;
	}
	
	/**
     * Indique si ce jeton est associe a une representation inconnue
     *
     * @return true si ce jeton est associe a une representation inconnue
     *   sinon false
     */
	public boolean estInconnu() {
		return type == Type.inconnu;
	}
	
	/**
     * Indique si ce jeton est associe a un identificateur
     *
     * @return true si ce jeton est associe a un identificateur
     *   sinon false
     */
	public boolean estIdentificateur() {
		return type == Type.identificateur;
	}
	
}
