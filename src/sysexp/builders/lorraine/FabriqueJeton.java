package sysexp.builders.lorraine;

/**
 * Classe representant une fabrique de jetons de la grammaire 
 *
 */
class FabriqueJeton {
	/**
     * Jeton associe au point virgule
     */
	private static Jeton pointVirgule = new Jeton(Type.pointVirgule, ";");
	
	/**
     * Jeton associe a la virgule
     */
	private static Jeton virgule = new Jeton(Type.virgule, ",");
	
	/**
     * Jeton associe au zero
     */
	private static Jeton zero = new Jeton(Type.zero, "0");
	
	/**
     * Jeton associe au signe egale
     */
	private static Jeton egale = new Jeton(Type.egale, "=");
	
	/**
     * Jeton associe au signe different
     */
	private static Jeton different = new Jeton(Type.different, "/=");
	
	/**
     * Jeton associe au signe inferieur ou egale
     */
	private static Jeton inferieurOuEgale = new Jeton(Type.inferieurOuEgale, "<=");
	
	/**
     * Jeton associe au signe superieur ou egale
     */
	private static Jeton superieurOuEgale = new Jeton(Type.superieurOuEgale, ">=");
	
	/**
	 * Jeton associe au signe superieur
	 */
	private static Jeton superieur = new Jeton(Type.superieur, ">");
	
	/**
     * Jeton associe au signe inferieur
     */
	private static Jeton inferieur = new Jeton(Type.inferieur, "<");
	
	/**
	 * Jeton associe au mot cle non
	 */
	private static Jeton non = new Jeton(Type.non, "non");
	
	/**
	 * Jeton associe au mot cle si
	 */
	private static Jeton si = new Jeton(Type.si, "si");
	
	/**
	 * Jeton associe au mot cle et
	 */
	private static Jeton et = new Jeton(Type.et, "et");
	
	/**
	 * Jeton associe au mot cle alors
	 */
	private static Jeton alors = new Jeton(Type.alors, "alors");
	
	/**
	 * Jeton associe a l'operateur moins
	 */
	private static Jeton operateurMoins = new Jeton(Type.operateurMoins, "-");
	
	/**
	 * Jeton associe a l'operateur plus
	 */
	private static Jeton operateurPlus = new Jeton(Type.operateurPlus, "+");
	
	/**
	 * Jeton associe a l'operateur multiplie
	 */
	private static Jeton operateurMultiplie = new Jeton(Type.operateurMultiplie, "*");
	
	/**
	 * Jeton associe a l'operateur divise
	 */
	private static Jeton operateurDivise = new Jeton(Type.operateurDivise, "/");
	
	/**
	 * Jeton associe a la parenthese ouvrante
	 */
	private static Jeton parentheseOuvrante = new Jeton(Type.parentheseOuvrante, "(");
	
	/**
	 * Jeton associe a la parenthese fermante
	 */
	private static Jeton parentheseFermante = new Jeton(Type.parentheseFermante, ")");
	
	/**
	 * Jeton associe aux faits booleen
	 */
	private static Jeton faitsBooleen = new Jeton(Type.faitsBooleen, "faits_booleens");
	
	/**
	 * Jeton associe aux faits symbolique
	 */
	private static Jeton faitsSymbolique = new Jeton(Type.faitsSymbolique, "faits_symboliques");
	
	/**
	 * Jeton associe aux faits entier
	 */
	private static Jeton faitsEntier = new Jeton(Type.faitsEntier, "faits_entiers");
	
	/**
	 * Jeton associe au tire du bas
	 */
	private static Jeton tirerDuBas = new Jeton(Type.tirerDuBas, "_");
	
	/**
	 * Jeton associe a la fin d'expression
	 */
	private static Jeton finExpression = new Jeton(Type.finExpression, "EOF");
	
	private FabriqueJeton() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne le jeton associe au point virgule
	 * @return le jeton associe au point virgule
	 */
	public static Jeton pointVirgule() {
		return pointVirgule;
	}

	/**
	 * Retourne le jeton associe a la virgule
	 * @return le jeton associe a la virgule
	 */
	public static Jeton virgule() {
		return virgule;
	}

	/**
	 * Retourne le jeton associe au signe egale
	 * @return le jeton associe au signe egale
	 */
	public static Jeton egale() {
		return egale;
	}

	/**
	 * Retourne le jeton associe au signe different
	 * @return le jeton associe au signe different
	 */
	public static Jeton different() {
		return different;
	}
	
	/**
	 * Retourne le jeton associe au signe inferieur ou egale
	 * @return le jeton associe au signe inferieur ou egale
	 */
	public static Jeton inferieurOuEgale() {
		return inferieurOuEgale;
	}

	/**
	 * Retourne le jeton associe au signe superieur ou egale
	 * @return le jeton associe au signe superieur ou egale
	 */
	public static Jeton superieurOuEgale() {
		return superieurOuEgale;
	}

	/**
	 * Retourne le jeton associe au signe superieur
	 * @return le jeton associe au signe superieur
	 */
	public static Jeton superieur() {
		return superieur;
	}

	/**
	 * Retourne le jeton associe au signe inferieur
	 * @return le jeton associe au signe inferieur
	 */
	public static Jeton inferieur() {
		return inferieur;
	}

	/**
	 * Retourne le jeton associe au mot cle non
	 * @return le jeton associe au mot cle non
	 */
	public static Jeton non() {
		return non;
	}

	/**
	 * Retourne le jeton associe au mot cle si
	 * @return le jeton associe au mot cle si
	 */
	public static Jeton si() {
		return si;
	}
	
	/**
	 * Retourne le jeton associe au mot cle et
	 * @return le jeton associe au mot cle et
	 */
	public static Jeton et() {
		return et;
	}

	/**
	 * Retourne le jeton associe au mot cle alors
	 * @return le jeton associe au mot cle alors
	 */
	public static Jeton alors() {
		return alors;
	}

	/**
	 * Retourne le jeton associe a l'operateur moins
	 * @return le jeton associe a l'operateur moins
	 */
	public static Jeton operateurMoins() {
		return operateurMoins;
	}

	/**
	 * Retourne le jeton associe a l'operateur plus
	 * @return le jeton associe a l'operateur pluss
	 */
	public static Jeton operateurPlus() {
		return operateurPlus;
	}

	/**
	 * Retourne le jeton associe a l'operateur multiplie
	 * @return le jeton associe a l'operateur multiplie
	 */
	public static Jeton operateurMultiplie() {
		return operateurMultiplie;
	}

	/**
	 * Retourne le jeton associe a l'operateur divise
	 * @return le jeton associe a l'operateur divise
	 */
	public static Jeton operateurDivise() {
		return operateurDivise;
	}

	/**
	 * Retourne le jeton associe a la parenthese ouvrante
	 * @return le jeton associe a la parenthese ouvrante
	 */
	public static Jeton parentheseOuvrante() {
		return parentheseOuvrante;
	}

	/**
	 * Retourne le jeton associe a la parenthese fermante
	 * @return le jeton associe a la parenthese fermante
	 */
	public static Jeton parentheseFermante() {
		return parentheseFermante;
	}
	
	/**
	 * Retourne le jeton associe au zero
	 * @return le jeton associe au zero
	 */
	public static Jeton zero(){
		return zero;
	}
	
	/**
	 * Retourne le jeton associe a un fait booleen
	 * @return le jeton associe a un fait booleen
	 */
	public static Jeton faitsBooleen() {
		return faitsBooleen;
	}

	/**
	 * Retourne le jeton associe a un fait symbolique
	 * @return le jeton associe a un fait symbolique
	 */
	public static Jeton faitsSymbolique() {
		return faitsSymbolique;
	}

	/**
	 * Retourne le jeton associe a un fait entier
	 * @return le jeton associe a un fait entier
	 */
	public static Jeton faitsEntier() {
		return faitsEntier;
	}

	/**
	 * Retourne le jeton associe au tire du bas
	 * @return le jeton associe au tire du bas
	 */
	public static Jeton tirerDuBas() {
		return tirerDuBas;
	}	
	
	/**
	 * Retourne le jeton associe a la fin d'expression
	 * @return le jeton associe a la fin d'expression
	 */
	public static Jeton finExpression(){
		return finExpression;
	}
	
	/**
     * Retourne le jeton associe a une representation d'un chiffre positif
     *
     * @param representation la representation  d'un chiffre positif
     * @return le jeton associe a une representation  d'un chiffre positif
     */
	public static Jeton chiffrePositif(String representation) {
		return new Jeton(Type.chiffrePositif, representation);
	}

	/**
     * Retourne le jeton associe a une representation inconnue
     *
     * @param representation la representation inconnue
     * @return le jeton associe a une representation inconnue
     */
	public static Jeton inconnu(String representation) {
		return new Jeton(Type.inconnu, representation);
	}
	
	/**
     * Retourne le jeton associe a la representation d'un identificateur
     *
     * @param representation la representation d'un identificateur
     * @return le jeton associe a la representation d'un identificateur
     */
	public static Jeton identificateur(String representation) {
		return new Jeton(Type.identificateur, representation);
	}
}
