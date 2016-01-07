package sysexp.builders.lorraine;

import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Classe representant un analyseur lexical de la grammaire
 * @author Darkham and Tanaky
 */
public class Lexical{
	private LineNumberReader reader;
	private int position;
	private String ligne;
	private Jeton jetonActuel;
	
	/**
     * Constructeur logique
     *
     * @param reader la valeur de {@link Lexical#reader}.
     */
	protected Lexical(LineNumberReader reader) {
		// TODO Auto-generated constructor stub
		this.reader = reader;
		this.position = 0;
		this.ligne = "";
		this.jetonActuel = null;
	}
	
	/**
     * Extrait puis retourne le jeton suivant
     *
     * @return le jeton suivant
     * @throw IOException si le flot d'entree ne peut etre lu
     */
	protected Jeton suivant() throws IOException{
		if (!avancer()) {
			this.jetonActuel = FabriqueJeton.finExpression();
			return this.jetonActuel;
		}
		//on echappe les espaces ici afin que la methode identificateur() teste les espaces pour connaitre la fin d'un mot
		while(Character.isWhitespace(ligne.charAt(position))){
			position++;
			if(!avancer()){
				this.jetonActuel = FabriqueJeton.finExpression();
				return this.jetonActuel;
			}
		}
		
		char c = ligne.charAt(position);
		
		switch (c) {
		case ';':
			position++;
			this.jetonActuel = FabriqueJeton.pointVirgule();
			break;
		case ',':
			position++;
			this.jetonActuel = FabriqueJeton.virgule();
			break;
		case '0':
			position++;
			this.jetonActuel = FabriqueJeton.zero();
			break;
		case '=':
			position++;
			this.jetonActuel = FabriqueJeton.egale();
			break;
		case '-':
			position++;
			this.jetonActuel = FabriqueJeton.operateurMoins();
			break;
		case '+':
			position++;
			this.jetonActuel = FabriqueJeton.operateurPlus();
			break;
		case '*':
			position++;
			this.jetonActuel = FabriqueJeton.operateurMultiplie();
			break;
		case '(':
			position++;
			this.jetonActuel = FabriqueJeton.parentheseOuvrante();
			break;
		case ')':
			position++;
			this.jetonActuel = FabriqueJeton.parentheseFermante();
			break;
		case '_':
			position++;
			this.jetonActuel = FabriqueJeton.tirerDuBas();
			break;
		// compose:
		case 'e':
			if(estUnMot(position, position + 2)){
				if(unMot(position, position + 2).equals("et")){
					position += 2;
					this.jetonActuel = FabriqueJeton.et();
					break;
				}
			}
			else{
				position++;
				this.jetonActuel = this.identificateur("e");
				break;
			}
		case 's':
			if(estUnMot(position, position + 2)){
				if(unMot(position, position + 2).equals("si")){
					position += 2;
					this.jetonActuel = FabriqueJeton.si();
					break;
				}
			}
			else{
				position++;
				this.jetonActuel = this.identificateur("s");
				break;
			}
		case 'n':
			if(estUnMot(position, position + 3)){
				if(unMot(position, position + 3).equals("non")){
					position += 3;
					this.jetonActuel = FabriqueJeton.non();
					break;
				}
			}
			else{
				position++;
				this.jetonActuel = this.identificateur("a");
				break;
			}
		case 'a':
			if(estUnMot(position, position + 5)){
				if(unMot(position, position+5).equals("alors")){
					position += 5;
					this.jetonActuel = FabriqueJeton.alors();
					break;
				}
			}
			else{
				position++;
				this.jetonActuel = this.identificateur("a");
				break;
			}
		case '>':
			if (ligne.length() > (position + 1) && Character.compare(ligne.charAt(position + 1), '=') == 0) {
				position += 2;
				this.jetonActuel = FabriqueJeton.superieurOuEgale();
				break;
			}
			position++;
			this.jetonActuel = FabriqueJeton.superieur();
			break;
		case '<':
			if (ligne.length() > (position + 1) && Character.compare(ligne.charAt(position + 1), '=') == 0) {
				position += 2;
				this.jetonActuel = FabriqueJeton.inferieurOuEgale();
				break;
			}
			position++;
			this.jetonActuel = FabriqueJeton.inferieur();
			break;
		case '/':
			if (ligne.length() > (position + 1) && Character.compare(ligne.charAt(position + 1), '=') == 0) {
				position += 2;
				this.jetonActuel = FabriqueJeton.different();
				break;
			}
			position++;
			this.jetonActuel = FabriqueJeton.operateurDivise();
			break;
		default:
			if (Character.isDigit(c)) {
				position++;
				this.jetonActuel = FabriqueJeton.chiffrePositif(Character.toString(c));
				break;
			}

			if (ligne.length() > (position + "faits_booleens".length())) {
				int current = position;
				if (ligne.substring(current, position + "faits_booleens".length()).equals("faits_booleens")) {
					position += "faits_booleens".length();
					this.jetonActuel = FabriqueJeton.faitsBooleen();
					break;
				}
			}
			if (ligne.length() > (position + "faits_symboliques".length())) {
				int current = position;
				if (ligne.substring(current, position + "faits_symboliques".length()).equals("faits_symboliques")) {
					position += "faits_symboliques".length();
					this.jetonActuel = FabriqueJeton.faitsSymbolique();
					break;
				}
			}
			if (ligne.length() > (position + "faits_entiers".length())) {
				int current = position;
				if (ligne.substring(current, position + "faits_entiers".length()).equals("faits_entiers")) {
					position += "faits_entiers".length();
					this.jetonActuel = FabriqueJeton.faitsEntier();
					break;
				}
			}
			if (Character.isLetter(c)) {
				position++;
				this.jetonActuel = this.identificateur(Character.toString(c));
				break;
			}
			else{
				this.jetonActuel = FabriqueJeton.inconnu(Character.toString(c));
				break;
			}

		}
		
		return this.jetonActuel;
	}

	/**
     * Tente d'avancer dans le flot
     *
     * @return true si nous avons pu avancer dans le flot sinon false
     */
	protected boolean avancer() throws IOException {
		while(true){
			if(position >= ligne.length()){
				
				ligne = reader.readLine();
				
				if(ligne == null)
					return false;
				
				position = 0;
			}
			else{
				return true;
			}
		}
	}
	
	/**
	 * Verifie si une chaine entre deux positions sur la ligne est un mot
	 * @param positionDebut
	 * @param positionFin
	 * @return
	 */
	private boolean estUnMot(int positionDebut, int positionFin){
		if(positionFin + 1 > ligne.length())
			return false;
		if(!ligne.substring(positionFin, positionFin + 1).equals(" "))
			return false;
		if(positionDebut != 0 && !ligne.substring(positionDebut - 1, positionDebut).equals(" "))
			return false;
		return true;
	}
	
	private String unMot(int positionDebut, int positionFin){
		return ligne.substring(positionDebut, positionFin);
	}
	
	/**
	 * Creer un jeton identificateur
	 * @param lettre La premiere lettre de l'identificateur
	 * @return
	 * @throws IOException si le flot d'entree ne peut etre lu
	 */
	private Jeton identificateur(String lettre) throws IOException{
		boolean loop = true;
		String representation = lettre;
		
		while(loop = this.avancer()){
			char currentCharacter = ligne.charAt(position);
			if( Character.compare('_', currentCharacter ) == 0 ){
				representation += currentCharacter;
				position++;
				if(!this.avancer())
					return FabriqueJeton.finExpression();
				else
					currentCharacter = ligne.charAt(position);
			}
			
			if(Character.isDigit(currentCharacter) || Character.isLetter(currentCharacter)){
				representation += currentCharacter;
				position++;
			}
			else{
				break;
			}
		}
		
		if(!loop){
			return FabriqueJeton.finExpression();
		}
		
		return FabriqueJeton.identificateur(representation);
	}
	
	/**
	 * Retourne une chaine de caractere representant le curseur de Lexical sur la ligne
	 * @return
	 */
	protected String getCursor(){
		if(this.position >= this.ligne.length())
			this.position = this.ligne.length() - 1;
		
		String value = "Symbole: " + this.lireJetonActuel().lireRepresentation() + "\n[Ligne>";
		value += this.reader.getLineNumber() + "]";
		value += ligne.substring(0, position) + "[";
		value += ligne.charAt(position) + "]";
		value += ligne.substring(position + 1);
		return value;
	}
	
	/**
	 * Retourne le jeton actuel
	 * @return
	 */
	protected Jeton lireJetonActuel(){
		return this.jetonActuel;
	}
}
